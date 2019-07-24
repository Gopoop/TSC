package com.gopoop.bd.tsc.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 郭速凯
 * @date 2019/7/22 11:41
 */
@Data
@ApiModel
public class PageProcessBean {
    @ApiModelProperty(notes = "id")
    private Integer id;
    @ApiModelProperty(notes = "爬虫id")
    private Integer spiderId;
    @ApiModelProperty(notes = "爬虫名称")
    private String spiderName;
    @ApiModelProperty(notes = "提取规则")
    private String rule;
    @ApiModelProperty(notes = "属性名称")
    private String attribute;
    @ApiModelProperty(notes = "持久化字段名")
    private String fieldEn;
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
    @ApiModelProperty(notes = "后续抓取url提取规则")
    private String targetRequestsRegex;
}
