package com.gopoop.bd.tsc.jdbc.sql;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @date 2019/7/19 12:10
 */
@Data
@ApiModel
public class Field {

    @ApiModelProperty(notes = "提取属性名，持久化字段名称")
    private String name;
    @ApiModelProperty(notes = "字段中文名，界面展示使用")
    private String nameCn;
    @ApiModelProperty(notes = "字段长度")
    private Integer length;
    @ApiModelProperty(notes = "字段类型")
    private String type;
    @ApiModelProperty(notes = "字段是否展示在页面上")
    private Boolean show;
}
