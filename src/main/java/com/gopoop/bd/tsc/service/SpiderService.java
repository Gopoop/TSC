package com.gopoop.bd.tsc.service;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.gopoop.bd.tsc.entity.SpiderEntity;
import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;
import com.gopoop.bd.tsc.spider.PipelineConfig;
import com.gopoop.bd.tsc.spider.SiteConfig;
import com.gopoop.bd.tsc.spider.SpiderManager;
import com.gopoop.bd.tsc.spider.pageprocess.PageProcessConfig;
import com.gopoop.bd.tsc.vo.PageBean;
import com.gopoop.bd.tsc.vo.resp.SpiderBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;


/**
 * @author 郭速凯
 * @date 2019/7/25 11:17
 */
@Service
public class SpiderService{

    public JdbcService getJdbcService() {
        return jdbcService;
    }

    @Autowired
    private JdbcService jdbcService;

    @Autowired
    private SpiderManager spiderManager;


    public PageBean<SpiderBean> page(SqlExecuteObject executeObject){
        Long count = jdbcService.count(executeObject);
        if(count > 0){
            List<SpiderEntity> entities = jdbcService.listObject(executeObject,SpiderEntity.class);
            List<SpiderBean> beans = new ArrayList<>();
            for (SpiderEntity entity : entities) {
                SpiderBean bean = new SpiderBean();
                BeanUtil.copyProperties(entity,bean,new String[]{"pageProcessConfigs","pipelineConfig","siteConfig"});
                beans.add(getRunningInfo(bean));
            }
            return new PageBean<>(beans,count);
        }
        return new PageBean<>(new ArrayList<>(),0L);
    }

    public SpiderBean selectOne(SqlExecuteObject executeObject){
        SpiderEntity entity = getJdbcService().queryOneObject(executeObject,SpiderEntity.class);
        if(entity != null){
            SpiderBean bean = new SpiderBean();
            //配置信息转换
            BeanUtil.copyProperties(entity,bean,new String[]{"pageProcessConfigs","pipelineConfig","siteConfig"});
            bean.setPageProcessConfigs(JSONObject.parseArray(entity.getPageProcessConfigs(),PageProcessConfig.class));
            bean.setPipelineConfig(JSONObject.parseObject(entity.getPipelineConfig(),PipelineConfig.class));
            bean.setSiteConfig(JSONObject.parseObject(entity.getSiteConfig(),SiteConfig.class));
            return getRunningInfo(bean);
        }
        return null;
    }

    /**
     * 获取爬虫运行信息
     * @param bean
     * @return
     */
    private SpiderBean getRunningInfo(SpiderBean bean){
        Spider spider = spiderManager.get(bean.getId());
        bean.setStatus(spider.getStatus());
        bean.setPageCount(spider.getPageCount());
        bean.setStartTime(spider.getStartTime());
        return bean;
    }


    /**
     * 保存
     * @param entity
     * @param restart
     * @return
     */
    public Integer save(SpiderEntity entity, Boolean restart) {
        Integer id = entity.getId();
        if(entity.getId() == null || entity.getId() == 0){
            id = getJdbcService().insert(SqlExecuteObject.builder().fieldValueMap(entity).tableName("spider").build());
            if(id > 0){
                spiderManager.initAndCacheSpiderInstance(entity,restart);
            }
        }else{
            int result = getJdbcService().update(SqlExecuteObject.builder().fieldValueMap(entity).tableName("spider").build());
            if(result > 0){
                spiderManager.modify(entity,restart);

            }
        }
        return id;
    }
}
