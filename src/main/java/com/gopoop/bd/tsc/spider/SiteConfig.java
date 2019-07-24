package com.gopoop.bd.tsc.spider;

import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 *  网站配置
 * @author gopoop 2019-07-17
 */
@Data
public class SiteConfig  {
    /**
     * 为空默认是utf-8
     */
    private String charset;

    /**
     * user_agent
     */
    private String userAgent;

    /**
     * 超市时间 默认3秒
     */
    private Integer timeOut;

    /**
     * 重试次数 默认3次
     */
    private Integer retryTime;

    /**
     * 循环重试次数 默认3次
     */
    private Integer cycleRetryTime;

    /**
     * cookies 列表
     */
    private List<String> cookies;

    /**
     * 域名
     */
    private String domain;

    /**
     * headers
     */
    private List<String> headers;

    /**
     * http代理 如127.0.0.1:8080
     */
    private String httpProxy;


}
