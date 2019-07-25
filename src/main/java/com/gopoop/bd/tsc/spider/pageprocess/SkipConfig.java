package com.gopoop.bd.tsc.spider.pageprocess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 页面跳过配置
 */
@Data
@ApiModel
public class SkipConfig {
    @ApiModelProperty(notes = "属性")
    private String attribute;
    @ApiModelProperty(notes = "值")
    private String value;
    @ApiModelProperty(notes = "是否相等  是表示 使用=比较 false 表示使用 ！= 比较")
    private boolean equals;
}
