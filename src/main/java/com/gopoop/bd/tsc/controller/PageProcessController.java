package com.gopoop.bd.tsc.controller;

import cn.hutool.json.JSONUtil;
import com.gopoop.bd.tsc.entity.PageProcessEntity;
import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;
import com.gopoop.bd.tsc.jdbc.sql.generator.InsertSqlGenerator;
import com.gopoop.bd.tsc.jdbc.sql.generator.SqlGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 属性提取配置
 * @author gopoop 2019-07-17
 */
@RestController
@RequestMapping("/pageProcess")
public class PageProcessController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("test")
    public String test(){
        PageProcessEntity entity = new PageProcessEntity();
        entity.setAttribute("String");
        entity.setFieldCn("String");
        entity.setFieldEn("String");
        entity.setFieldIsShow(1);
        entity.setFieldLength(100);

        SqlGenerator generator = new InsertSqlGenerator();
        String sql = generator.generate(SqlExecuteObject.builder().fieldValueMap(entity).tableName(entity.getClass().getSimpleName()).build());
        return "test" + jdbcTemplate.update(sql);
    }




}

