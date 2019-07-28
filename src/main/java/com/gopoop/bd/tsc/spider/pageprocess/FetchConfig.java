package com.gopoop.bd.tsc.spider.pageprocess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 提取配置
 * @date 2019/7/25 15:55
 */
@Data
@ApiModel
public class FetchConfig {
    @ApiModelProperty(notes = "属性提取源头")
    private Source source;
    @ApiModelProperty(notes = "属性提取配置")
    private List<SelectorConfig> selectorConfigs;
    @ApiModelProperty(notes = "提取属性名，持久化字段名称")
    private String attribute;
}
