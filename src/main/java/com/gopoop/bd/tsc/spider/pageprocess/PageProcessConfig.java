package com.gopoop.bd.tsc.spider.pageprocess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 *页面处理配置
 * @date 2019/7/17 18:54
 */
@Data
@ApiModel
public class PageProcessConfig{
    @ApiModelProperty(notes = "字段提取规则")
    private List<FetchConfig> fieldFetchConfigs;
    @ApiModelProperty(notes = "页面跳过规则")
    private List<SkipConfig> skipConditions;
    @ApiModelProperty(notes = "后续抓取url提取规则")
    private List<FetchConfig> targetRequestsFetchConfigs;

}
