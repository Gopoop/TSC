package com.gopoop.bd.tsc.spider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.gopoop.bd.tsc.common.utils.SqlUtil;
import com.gopoop.bd.tsc.common.utils.StringUtils;
import com.gopoop.bd.tsc.entity.SpiderEntity;
import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;
import com.gopoop.bd.tsc.jdbc.sql.generator.InsertSqlGenerator;
import com.gopoop.bd.tsc.jdbc.sql.generator.SqlGenerator;
import com.gopoop.bd.tsc.service.JdbcService;
import com.gopoop.bd.tsc.spider.pageprocess.*;
import com.gopoop.bd.tsc.spider.pipeline.PipelineConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @date 2019/7/24 15:49
 */
@Slf4j
@Component
public class SpiderManager {
    /**
     * 爬虫实例缓存
     */
    private static final Map<Integer, Spider> spiderPool = new ConcurrentHashMap<>();

    @Autowired
    private JdbcService jdbcService;

    /**
     * 初始化爬虫池
     */
    @PostConstruct
    public void init() {
        List<SpiderEntity> spiders = jdbcService.listObject(SqlExecuteObject.builder().tableName("spider").build(),SpiderEntity.class);
        if(CollectionUtil.isNotEmpty(spiders)){
            for (SpiderEntity spider : spiders) {
                this.initAndCacheSpiderInstance(spider, Boolean.FALSE);
            }
        }
    }

    /**
     * 初始化爬虫
     * @param spiderEntity
     * @param restart
     */
    public void initAndCacheSpiderInstance(SpiderEntity spiderEntity, boolean restart){
        List<PageProcessConfig> pageProcessConfigs = JSONObject.parseArray(spiderEntity.getPageProcessConfigs(),PageProcessConfig.class);
        PipelineConfig pipelineConfig = JSONObject.parseObject(spiderEntity.getPipelineConfig(),PipelineConfig.class);
        SiteConfig siteConfig = JSONObject.parseObject(spiderEntity.getSiteConfig(),SiteConfig.class);
        //创建爬虫
        Spider spider = Spider.create(this.configurationPageProcessor(pageProcessConfigs,siteConfig));
        spider.addPipeline(this.configurationPipeline(pipelineConfig));
        spider.thread(spiderEntity.getThread());
        spider.addUrl(spiderEntity.getUrl());
        spider.test(spiderEntity.getTestUrl());
        spider.setUUID(spiderEntity.getName());
        spiderPool.put(spiderEntity.getId(),spider);
        //是否启动
        if(restart){
            this.start(spiderEntity.getId());
        }
    }



    /**
     * 启动爬虫
     * @param spiderId
     */
    public void start(Integer spiderId){
        Spider spider = spiderPool.get(spiderId);
        spider.start();
        //TODO 更新启动时间
    }

    /**
     * 暂停爬虫
     * @param spiderId
     */
    public void stop(Integer spiderId){
        Spider spider = spiderPool.get(spiderId);
        spider.stop();
        //TODO 记录暂停时间  记录爬取的数量等信息
    }

    /**
     * 获取一个爬虫实例
     * @param spiderId
     * @return
     */
    public Spider get(Integer spiderId){
        return spiderPool.get(spiderId);
    }

    /**
     * 移除一个爬虫实例
     * @param spiderId
     */
    public void remove(Integer spiderId) {
        Spider spider = this.get(spiderId);
        if(spider != null && spider.getStatus() == Spider.Status.Running){
            this.stop(spiderId);
            spiderPool.remove(spiderId);
        }
    }

    /**
     * 修改爬虫配置
     * @param entity
     */
    public void modify(SpiderEntity entity,boolean restart) {
        this.remove(entity.getId());
        this.initAndCacheSpiderInstance(entity,restart);
    }

    /**
     * 生成配置化页面处理器实例
     * @param pageProcessConfigs
     * @param siteConfig
     * @return
     */
    private PageProcessor configurationPageProcessor(List<PageProcessConfig> pageProcessConfigs, SiteConfig siteConfig) {
        return new PageProcessor() {
            @Override
            public void process(Page page) {
                for (PageProcessConfig pageProcessConfig : pageProcessConfigs) {
                    //抽取页面信息策略
                    if(CollectionUtil.isNotEmpty(pageProcessConfig.getFieldFetchConfigs())){
                        page = this.initFieldFetchPolicy(pageProcessConfig.getFieldFetchConfigs(),page);
                    }
                    //页面跳过策略
                    if(CollectionUtil.isNotEmpty(pageProcessConfig.getSkipConditions())){
                        page = this.initSkipPolicy(pageProcessConfig.getSkipConditions(),page);
                    }
                    //后续爬取url地址
                    if(CollectionUtil.isNotEmpty(pageProcessConfig.getTargetRequestsFetchConfigs())){
                        page = this.initTargetRequestFetchPolicy(pageProcessConfig.getTargetRequestsFetchConfigs(),page);
                    }
                }
            }

            /**
             * 初始化后续爬取url地址获取策略
             * @param targetRequestsFetchConfigs
             * @param page
             * @return
             */
            private Page initTargetRequestFetchPolicy(List<FetchConfig> targetRequestsFetchConfigs, Page page) {
                for (FetchConfig config : targetRequestsFetchConfigs) {
                    page.addTargetRequests(this.getSelectable(config,page).all());
                }
                return page;
            }

            /**
             * 初始化页面跳过策略
             * @param skipConditions
             * @param page
             * @return
             */
            private Page initSkipPolicy(List<SkipConfig> skipConditions, Page page) {
                for (SkipConfig skipCondition : skipConditions) {
                    String value = page.getResultItems().get(skipCondition.getAttribute());
                    //如果属性值不存在，默认赋予null
                    if(value == null){
                        value = StringUtils.NULL;
                    }
                    if(StringUtils.isEmpty(skipCondition.getValue())){
                        skipCondition.setValue(StringUtils.NULL);
                    }
                    //比较相等
                    if(skipCondition.isEquals()){
                        if(value.equalsIgnoreCase(skipCondition.getValue())){
                            page.setSkip(Boolean.TRUE);
                        }
                    }else{//比较不等
                        if(!value.equalsIgnoreCase(skipCondition.getValue())){
                            page.setSkip(Boolean.TRUE);
                        }
                    }
                }
                return page;
            }

            /**
             * 初始化抽取页面信息获取策略
             * @param fieldFetchConfigs
             * @param page
             * @return
             */
            private Page initFieldFetchPolicy(List<FetchConfig> fieldFetchConfigs, Page page) {
                for (FetchConfig config : fieldFetchConfigs) {
                    page.putField(config.getAttribute(),this.getSelectable(config,page).toString());
                }
                return page;
            }

            /**
             * 获取抓取策略
             * @param config
             * @param page
             * @return
             */
            private Selectable getSelectable(FetchConfig config, Page page) {
                Selectable selectable;
                if(config.getSource() == Source.HTML){
                    selectable = page.getHtml();
                }else{
                    selectable = page.getUrl();
                }
                for (SelectorConfig selectorConfig : config.getSelectorConfigs()) {
                    switch (selectorConfig.getFunction()){
                        case REGEX:
                            selectable = selectable.regex(selectorConfig.getRegex());
                            break;
                        case XPATH:
                            selectable = selectable.xpath(selectorConfig.getRegex());
                            break;
                        case LINKS:
                            selectable = selectable.links();
                            break;
                        default:break;
                    }
                }
                return selectable;
            }


            @Override
            public Site getSite() {
                Site site = Site.me();
                BeanUtil.copyProperties(siteConfig,site);
                return site;
            }
        };
    }


    /**
     * 生成配置化持久化规则
     * @param pipelineConfig
     * @return
     */
    private Pipeline configurationPipeline(PipelineConfig pipelineConfig) {
        return (ResultItems resultItems, Task task) -> {
            switch (pipelineConfig.getPersistentWay()){
                case MYSQL:
                    this.mysqlPersist(resultItems.getAll(),pipelineConfig);
                    break;
                 default:
                     this.console(resultItems.getAll());
                     break;
            }
        };
    }

    /**
     * 数据库持久化
     * @param resultItems
     * @param pipelineConfig
     */
    private void mysqlPersist(Map<String,Object> resultItems, PipelineConfig pipelineConfig) {
        jdbcService.createTableIfNotExist(SqlExecuteObject.builder().tableName(pipelineConfig.getTableName()).fields(pipelineConfig.getFields()).build());
        jdbcService.insert(SqlExecuteObject.builder().tableName(pipelineConfig.getTableName()).fieldValueMap(resultItems).build());
    }

    /**
     * 控制台输出
     * @param all
     */
    private void console(Map<String, Object> all) {
        for (Map.Entry<String, Object> entry : all.entrySet()) {
            log.info("爬取的内容{}",JSONObject.toJSONString(entry));
        }
    }
}
