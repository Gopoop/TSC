package com.gopoop.bd.tsc.spider;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

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
    private Integer retryTime;
    @ApiModelProperty(notes = "循环重试次数 默认3次")
    private Integer cycleRetryTime;
    @ApiModelProperty(notes = "cookies 列表")
    private List<String> cookies;
    @ApiModelProperty(notes = "域名")
    private String domain;
    @ApiModelProperty(notes = "headers")
    private List<String> headers;
    @ApiModelProperty(notes = "http代理 如127.0.0.1:8080")
    private String httpProxy;


}
