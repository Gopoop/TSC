package com.gopoop.bd.tsc.spider.pageprocess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 提取配置
 */
@Data
@ApiModel
public class SelectorConfig {
    @ApiModelProperty(notes = "函数")
    private Function function;
    @ApiModelProperty(notes = "正则表达式")
    private String regex;
}
