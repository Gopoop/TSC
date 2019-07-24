package com.gopoop.bd.tsc.controller;


import com.gopoop.bd.tsc.entity.SpiderEntity;
import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;
import com.gopoop.bd.tsc.vo.ResponseVo;
import com.gopoop.bd.tsc.vo.req.SpiderPageRequest;
import com.gopoop.bd.tsc.vo.resp.SpiderBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Api(tags = "爬虫")
@RestController
@RequestMapping("/spider")
public class SpiderController extends BaseController<SpiderEntity, SpiderPageRequest, SpiderBean>{



    @Override
    protected String getTableName() {
        return "spider";
    }

    @Override
    protected SpiderBean copyProperties(Map<String,Object> entity) {
        return null;
    }

    @ApiOperation(value = "启动爬虫",httpMethod = "GET")
    @ApiImplicitParam(name="id",value = "id",dataType = "int",paramType = "path")
    @GetMapping("/start/{id}")
    public ResponseVo create(@PathVariable("id") Integer id){

        return ResponseVo.successResp();
    }



}

