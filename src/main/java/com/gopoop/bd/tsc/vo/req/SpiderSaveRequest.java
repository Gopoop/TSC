package com.gopoop.bd.tsc.vo.req;

import com.gopoop.bd.tsc.spider.pageprocess.PageProcessConfig;
import com.gopoop.bd.tsc.spider.PipelineConfig;
import com.gopoop.bd.tsc.spider.SiteConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class SpiderSaveRequest {
    @ApiModelProperty(notes = "id")
    private Integer id;
    @ApiModelProperty(notes = "名称")
    private String name;
    @ApiModelProperty(notes = "表名")
    private String tableName;
    @ApiModelProperty(notes = "线程数 默认5")
    private Integer thread;
    @ApiModelProperty(notes = "初始的url")
    private String url;
    @ApiModelProperty(notes = "测试网页")
    private String testUrl;
    @ApiModelProperty(notes = "数据来源 1 html 2 ajax")
    private Integer dataSource;
    @ApiModelProperty(notes = "启动类型 1 同步启动 2异步启动")
    private Integer startType;
    @ApiModelProperty(notes = "持久化方式 1 mysql 2 json文件")
    private Integer persistPattern;
    @ApiModelProperty(notes = "状态 0 初始化 1 运行中 2停止")
    private Integer stat;
    @ApiModelProperty(notes = "页面处理规则")
    private List<PageProcessConfig> pageProcessConfigs;
    @ApiModelProperty(notes = "持久化规则")
    private PipelineConfig pipelineConfig;
    @ApiModelProperty(notes = "网站配置")
    private SiteConfig siteConfig;
    @ApiModelProperty(notes = "是否重启")
    private Boolean restart;
}
