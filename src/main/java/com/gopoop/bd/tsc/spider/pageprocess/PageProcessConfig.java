package com.gopoop.bd.tsc.spider.pageprocess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @date 2019/7/17 18:54
 */
@Data
@ApiModel
public class PageProcessConfig{
    @ApiModelProperty(notes = "属性提取配置")
    private List<FieldFetchConfig> fieldFetchConfigs;
    @ApiModelProperty(notes = "提取属性名，持久化字段名称")
    private String field;
    @ApiModelProperty(notes = "字段中文名，用户界面展示")
    private String fieldCn;
    @ApiModelProperty(notes = "字段长度")
    private Integer fieldLength;
    @ApiModelProperty(notes = "字段类型")
    private String fieldType;
    @ApiModelProperty(notes = "字段是否展示")
    private Integer fieldIsShow;
    @ApiModelProperty(notes = "页面跳过规则  策略列表 json存储")
    private String skipCondition;
    @ApiModelProperty(notes = "后续抓取url提取正则表达式")
    private String targetRequestsRegex;

}
