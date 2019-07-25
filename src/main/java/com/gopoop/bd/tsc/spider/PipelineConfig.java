package com.gopoop.bd.tsc.spider;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 持久化配置
 * @date 2019/7/17 18:53
 */
@Data
@ApiModel
public class PipelineConfig {
    @ApiModelProperty(notes = "插入的sql")
    private String insertSql;
    @ApiModelProperty(notes = "建表的sql")
    private String tableSql;
    @ApiModelProperty(notes = "提取属性名，持久化字段名称")
    private String field;
    @ApiModelProperty(notes = "字段中文名，界面展示使用")
    private String fieldCn;
    @ApiModelProperty(notes = "字段长度")
    private Integer fieldLength;
    @ApiModelProperty(notes = "字段类型")
    private String fieldType;
    @ApiModelProperty(notes = "字段是否展示")
    private Integer fieldIsShow;
}
