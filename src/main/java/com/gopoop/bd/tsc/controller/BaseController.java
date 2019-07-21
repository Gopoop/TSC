package com.gopoop.bd.tsc.controller;

import cn.hutool.core.util.ClassUtil;
import com.gopoop.bd.tsc.entity.PageProcessEntity;
import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;
import com.gopoop.bd.tsc.service.JdbcService;
import com.gopoop.bd.tsc.vo.ResponseVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 郭速凯
 * @date 2019/7/19 17:42
 */
public abstract class BaseController<T> {

    @Autowired
    private JdbcService jdbcService;

    @ApiOperation(value = "新增接口",httpMethod = "POST")
    @PostMapping("/create")
    public ResponseVo<Integer> create(@RequestBody  T entity){
        int id = jdbcService.insert(SqlExecuteObject.builder().fieldValueMap(entity).tableName(ClassUtil.getClass(entity).getSimpleName()).build());
        return ResponseVo.successResp(id);
    }

    @ApiOperation(value = "更新接口",httpMethod = "PUT")
    @PutMapping("/update")
    public ResponseVo<Integer> update(@RequestBody T entity){
        jdbcService.update(SqlExecuteObject.builder().fieldValueMap(entity).tableName(ClassUtil.getClass(entity).getSimpleName()).build());
        return ResponseVo.successResp();
    }
}
