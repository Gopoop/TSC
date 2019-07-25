package com.gopoop.bd.tsc.spider;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 *  网站配置
 * @author gopoop 2019-07-17
 */
@Data
@ApiModel
public class SiteConfig  {
    @ApiModelProperty(notes = "编码  默认是utf-8")
    private String charset;
    @ApiModelProperty(notes = "userAgent")
    private String userAgent;
    @ApiModelProperty(notes = "超市时间 默认3秒")
    private Integer timeOut;
    @ApiModelProperty(notes = "重试次数 默认3次")
    private Integer retryTimes;
    @ApiModelProperty(notes = "循环重试次数 默认3次")
    private Integer cycleRetryTimes;
    @ApiModelProperty(notes = "cookies")
    private Map<String, String> cookies;
    @ApiModelProperty(notes = "域名")
    private String domain;
    @ApiModelProperty(notes = "headers")
    private Map<String, String> headers;
    @ApiModelProperty(notes = "http代理 如127.0.0.1:8080")
    private String httpProxy;


}
