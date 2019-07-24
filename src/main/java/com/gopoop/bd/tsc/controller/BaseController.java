package com.gopoop.bd.tsc.controller;

import com.gopoop.bd.tsc.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @date 2019/7/19 17:42
 */
public abstract class BaseController {

    @Autowired
    private JdbcService jdbcService;

    public JdbcService getJdbcService(){
        return jdbcService;
    }


}
