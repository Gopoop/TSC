package com.gopoop.bd.tsc.controller;


import com.gopoop.bd.tsc.entity.PageProcessEntity;
import com.gopoop.bd.tsc.entity.PipelineEntity;
import com.gopoop.bd.tsc.vo.req.PipelinePageRequest;
import com.gopoop.bd.tsc.vo.resp.PageProcessBean;
import com.gopoop.bd.tsc.vo.resp.PipelineBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Api(tags = "持久化配置")
@RestController
@RequestMapping("/pipeline")
public class PipelineController extends BaseController<PipelineEntity, PipelinePageRequest, PipelineBean>{



    @Override
    protected String getTableName() {
        return "pipeline";
    }

    @Override
    protected Class<PipelineEntity> getEntityClass() {
        return PipelineEntity.class;
    }

}

