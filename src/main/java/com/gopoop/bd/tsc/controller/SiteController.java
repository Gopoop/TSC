package com.gopoop.bd.tsc.controller;


import com.gopoop.bd.tsc.entity.SiteEntity;
import com.gopoop.bd.tsc.vo.req.SitePageRequest;
import com.gopoop.bd.tsc.vo.resp.SiteBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Api(tags = "网站配置")
@RestController
@RequestMapping("/site")
public class SiteController extends BaseController<SiteEntity, SitePageRequest, SiteBean>{



    @Override
    protected String getTableName() {
        return "site";
    }

    @Override
    protected Class<SiteEntity> getEntityClass() {
        return SiteEntity.class;
    }

    @Override
    protected SiteBean copyProperties(SiteEntity siteEntity) {
        return null;
    }

}

