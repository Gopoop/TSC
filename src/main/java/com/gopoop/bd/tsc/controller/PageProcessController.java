package com.gopoop.bd.tsc.controller;

import cn.hutool.json.JSONUtil;
import com.gopoop.bd.tsc.entity.PageProcessEntity;
import com.gopoop.bd.tsc.jdbc.sql.Condition;
import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;
import com.gopoop.bd.tsc.jdbc.sql.generator.InsertSqlGenerator;
import com.gopoop.bd.tsc.jdbc.sql.generator.SqlGenerator;
import com.gopoop.bd.tsc.vo.req.PageProcessPageRequest;
import com.gopoop.bd.tsc.vo.resp.PageProcessBean;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 属性提取配置
 * @author gopoop 2019-07-17
 */
@Api(tags = "属性提取配置")
@RestController
@RequestMapping("/pageProcess")
public class PageProcessController extends BaseController<PageProcessEntity, PageProcessPageRequest, PageProcessBean>{


    @Override
    protected List<Condition> getCondition(PageProcessPageRequest pageProcessPageRequest) {

        return null;
    }

    @Override
    protected String getTableName() {
        return "page_process";
    }
}

