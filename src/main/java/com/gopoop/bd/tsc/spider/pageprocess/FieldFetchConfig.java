package com.gopoop.bd.tsc.spider.pageprocess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class FieldFetchConfig {
    @ApiModelProperty(notes = "函数 如 getUrl() regex() ")
    private String function;
    @ApiModelProperty(notes = "正则表达式")
    private String regex;
}
