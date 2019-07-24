package com.gopoop.bd.tsc.spider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.gopoop.bd.tsc.entity.SpiderEntity;
import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;
import com.gopoop.bd.tsc.service.JdbcService;
import com.gopoop.bd.tsc.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @date 2019/7/24 15:49
 */
@Component
public class SpiderManager {
    /**
     * 爬虫实例缓存
     */
    private static final Map<Integer, Spider> spiderCache = new ConcurrentHashMap<>();

    @Autowired
    private JdbcService jdbcService;

    /**
     * 初始化爬虫池
     */
    @PostConstruct
    public void initPool() {
        List<Map<String,Object>> spiderMaps = jdbcService.list(SqlExecuteObject.builder().tableName("spider").build());
        for (Map<String, Object> spiderMap : spiderMaps) {
            SpiderEntity spiderEntity = BeanUtil.mapToBean(spiderMap,SpiderEntity.class,true);
            this.initAndCacheSpiderInstance(spiderEntity);
        }
    }

    /**
     * 初始化爬虫
     * @param spiderEntity
     */
    public void initAndCacheSpiderInstance(SpiderEntity spiderEntity){
        List<PageProcessConfig> pageProcessConfigs = JSONObject.parseArray(spiderEntity.getPageProcessConfigs(),PageProcessConfig.class);
        PipelineConfig pipelineConfig = JSONObject.parseObject(spiderEntity.getPipelineConfig(),PipelineConfig.class);
        SiteConfig siteConfig = JSONObject.parseObject(spiderEntity.getSiteConfig(),SiteConfig.class);
        Spider spider = Spider.create(new PageProcessor() {
            @Override
            public void process(Page page) {
                pageProcessConfigs.size();
            }

            @Override
            public Site getSite() {
                return Site.me().setCharset(siteConfig.getCharset());
            }
        });
        spider.addPipeline(new Pipeline() {
            @Override
            public void process(ResultItems resultItems, Task task) {
                pipelineConfig.toString();
            }
        });
        spiderCache.put(spiderEntity.getId(),spider);
    }

    /**
     * 启动爬虫
     * @param spiderId
     */
    public void start(Integer spiderId){
        Spider spider = spiderCache.get(spiderId);
        spider.start();
        //TODO 更新启动时间
    }

    /**
     * 暂停爬虫
     * @param spiderId
     */
    public void stop(Integer spiderId){
        Spider spider = spiderCache.get(spiderId);
        spider.stop();
        //TODO 记录暂停时间  记录爬取的数量等信息
    }

}
