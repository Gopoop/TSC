package com.gopoop.bd.tsc.controller;


import cn.hutool.core.bean.BeanUtil;
import com.gopoop.bd.tsc.entity.PageProcessEntity;

import com.gopoop.bd.tsc.vo.req.PageProcessPageRequest;
import com.gopoop.bd.tsc.vo.resp.PageProcessBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 属性提取配置
 * @author gopoop 2019-07-17
 */
@Api(tags = "属性提取配置")
@RestController
@RequestMapping("/pageProcess")
public class PageProcessController extends BaseController<PageProcessEntity, PageProcessPageRequest, PageProcessBean>{



    @Override
    protected String getTableName() {
        return "page_process";
    }

    @Override
    protected Class<PageProcessEntity> getEntityClass() {
        return PageProcessEntity.class;
    }

    @Override
    protected PageProcessBean copyProperties(PageProcessEntity pageProcessEntity) {
        PageProcessBean bean = new PageProcessBean();
        BeanUtil.copyProperties(pageProcessEntity,bean);
        return bean;
    }

}

