package com.gopoop.bd.tsc.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.gopoop.bd.tsc.common.utils.SqlUtil;
import com.gopoop.bd.tsc.jdbc.sql.Compare;
import com.gopoop.bd.tsc.jdbc.sql.Condition;
import com.gopoop.bd.tsc.jdbc.sql.PageParam;
import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;
import com.gopoop.bd.tsc.service.JdbcService;
import com.gopoop.bd.tsc.vo.PageBean;
import com.gopoop.bd.tsc.vo.ResponseVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author 郭速凯
 * @date 2019/7/19 17:42
 */
public abstract class BaseController<Entity,Req extends PageParam,Bean> {

    @Autowired
    private JdbcService jdbcService;

    /**
     * 获取表名
     * @return
     */
    protected abstract String getTableName();


    /**
     * 自定义数据复制
     * @param entity
     * @return
     */
    protected abstract Bean copyProperties(Map<String,Object> entity);

    @ApiOperation(value = "新增接口",httpMethod = "POST")
    @PostMapping("/create")
    public ResponseVo<Integer> create(@RequestBody  Entity entity){
        int id = jdbcService.insert(SqlExecuteObject.builder().fieldValueMap(entity).tableName(this.getTableName()).build());
        return ResponseVo.successResp(id);
    }

    @ApiOperation(value = "更新接口",httpMethod = "PUT")
    @PutMapping("/update")
    public ResponseVo<Integer> update(@RequestBody Entity entity){
        jdbcService.update(SqlExecuteObject.builder().fieldValueMap(entity).tableName(this.getTableName()).build());
        return ResponseVo.successResp();
    }

    @ApiOperation(value = "列表获取接口",httpMethod = "POST")
    @PostMapping("/page")
    public ResponseVo<PageBean<Bean>> page(@RequestBody Req req) throws IllegalAccessException {
        SqlExecuteObject sqlExecuteObject = SqlExecuteObject.builder()
                .conditions(req)
                .tableName(this.getTableName())
                .pageParam(PageParam.builder().pageNow(req.getPageNow()).pageSize(req.getPageSize()).build())
                .build();
        PageBean pageBean = jdbcService.page(sqlExecuteObject);
        if(CollectionUtil.isNotEmpty(pageBean.getList())){
            List<Bean> beans = new LinkedList<>();
            for (Object entity: pageBean.getList()) {
                beans.add(this.copyProperties((Map<String, Object>)entity));
            }
            pageBean.setList(beans);
        }
        return ResponseVo.successResp(pageBean);
    }

    @ApiOperation(value = "通过id获取",httpMethod = "GET")
    @ApiImplicitParam(name="id",value = "id",dataType = "int",paramType = "path")
    @GetMapping("/{id}")
    public ResponseVo<Bean> page(@PathVariable("id") Integer id){
        SqlExecuteObject sqlExecuteObject = SqlExecuteObject.builder()
                .condition(Condition.builder().field(SqlUtil.ID).value(id).build())
                .tableName(this.getTableName())
                .build();
        return ResponseVo.successResp(this.copyProperties(jdbcService.selectOne(sqlExecuteObject)));
    }
}
