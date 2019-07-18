package com.gopoop.bd.tsc.controller;

import cn.hutool.json.JSONUtil;
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
        return JSONUtil.toJsonStr(jdbcTemplate.queryForMap("SELECT * from page_process where id = 1"));
    }

}

