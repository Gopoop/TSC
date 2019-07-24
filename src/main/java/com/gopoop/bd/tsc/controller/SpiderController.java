package com.gopoop.bd.tsc.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.gopoop.bd.tsc.common.utils.SqlUtil;
import com.gopoop.bd.tsc.entity.SpiderEntity;
import com.gopoop.bd.tsc.jdbc.sql.Condition;
import com.gopoop.bd.tsc.jdbc.sql.PageParam;
import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;
import com.gopoop.bd.tsc.vo.PageBean;
import com.gopoop.bd.tsc.vo.ResponseVo;
import com.gopoop.bd.tsc.vo.req.SpiderPageRequest;
import com.gopoop.bd.tsc.vo.req.SpiderSaveRequest;
import com.gopoop.bd.tsc.vo.resp.SpiderBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Api(tags = "爬虫")
@RestController
@RequestMapping("/spider")
public class SpiderController extends BaseController{



    protected String getTableName() {
        return "spider";
    }

    protected SpiderBean copyProperties(Map<String,Object> entity) {
        return null;
    }

    @ApiOperation(value = "启动爬虫",httpMethod = "GET")
    @ApiImplicitParam(name="id",value = "id",dataType = "int",paramType = "path")
    @GetMapping("/start/{id}")
    public ResponseVo create(@PathVariable("id") Integer id){
        return ResponseVo.successResp();
    }


    @ApiOperation(value = "保存接口",httpMethod = "POST")
    @PostMapping("/save")
    public ResponseVo<Integer> save(@RequestBody SpiderSaveRequest saveRequest){
        Integer id = saveRequest.getId();
        //对象字段转换字符串
        SpiderEntity entity = new SpiderEntity();
        BeanUtil.copyProperties(saveRequest,entity,new String[]{"pageProcessConfigs","pipelineConfig","siteConfig"});
        entity.setPageProcessConfigs(JSONObject.toJSONString(saveRequest.getPageProcessConfigs()));
        entity.setPipelineConfig(JSONObject.toJSONString(saveRequest.getPipelineConfig()));
        entity.setSiteConfig(JSONObject.toJSONString(saveRequest.getSiteConfig()));
        if(entity.getId() == null || entity.getId() == 0){
            id = getJdbcService().insert(SqlExecuteObject.builder().fieldValueMap(entity).tableName(this.getTableName()).build());
        }else{
            getJdbcService().update(SqlExecuteObject.builder().fieldValueMap(entity).tableName(this.getTableName()).build());
        }
        return ResponseVo.successResp(id);
    }

    @ApiOperation(value = "列表获取接口",httpMethod = "POST")
    @PostMapping("/page")
    public ResponseVo<PageBean<SpiderBean>> page(@RequestBody SpiderPageRequest req) throws IllegalAccessException {
        SqlExecuteObject sqlExecuteObject = SqlExecuteObject.builder()
                .conditions(req)
                .tableName(this.getTableName())
                .pageParam(PageParam.builder().pageNow(req.getPageNow()).pageSize(req.getPageSize()).build())
                .build();
        PageBean pageBean = getJdbcService().page(sqlExecuteObject);
        if(CollectionUtil.isNotEmpty(pageBean.getList())){
            List<SpiderBean> beans = new LinkedList<>();
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
    public ResponseVo<SpiderBean> page(@PathVariable("id") Integer id){
        SqlExecuteObject sqlExecuteObject = SqlExecuteObject.builder()
                .condition(Condition.builder().field(SqlUtil.ID).value(id).build())
                .tableName(this.getTableName())
                .build();
        return ResponseVo.successResp(this.copyProperties(getJdbcService().selectOne(sqlExecuteObject)));
    }

}

