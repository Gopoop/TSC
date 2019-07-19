package com.gopoop.bd.tsc.controller;

import cn.hutool.core.util.ClassUtil;
import com.gopoop.bd.tsc.entity.PageProcessEntity;
import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;
import com.gopoop.bd.tsc.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 郭速凯
 * @date 2019/7/19 17:42
 */
public abstract class BaseController<T> {

    @Autowired
    private JdbcService jdbcService;

    @RequestMapping("/create")
    public String create(T entity){
        jdbcService.insert(SqlExecuteObject.builder().fieldValueMap(entity).tableName(ClassUtil.getClass(entity).getSimpleName()).build());
        return "success";
    }
}
