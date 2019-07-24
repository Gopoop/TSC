package com.gopoop.bd.tsc.controller;


import com.gopoop.bd.tsc.entity.SpiderEntity;
import com.gopoop.bd.tsc.vo.req.SpiderPageRequest;
import com.gopoop.bd.tsc.vo.resp.SpiderBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "爬虫")
@RestController
@RequestMapping("/spider")
public class SpiderController extends BaseController<SpiderEntity, SpiderPageRequest, SpiderBean>{



    @Override
    protected String getTableName() {
        return "spider";
    }

    @Override
    protected Class<SpiderEntity> getEntityClass() {
        return SpiderEntity.class;
    }

    @Override
    protected SpiderBean copyProperties(SpiderEntity spiderEntity) {
        return null;
    }

}

