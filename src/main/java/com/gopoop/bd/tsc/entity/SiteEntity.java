package com.gopoop.bd.tsc.entity;

import java.io.Serializable;
import lombok.Data;
import java.util.Date;

/**
 *  网站配置
 * @author gopoop 2019-07-17
 */
@Data
public class SiteEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 爬虫id
     */
    private Integer spiderId;

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
     * cookies 列表 用json存储
     */
    private String cookies;

    /**
     * 域名
     */
    private String domain;

    /**
     * headers 用json存储
     */
    private String headers;

    /**
     * http代理 如127.0.0.1:8080
     */
    private String httpProxy;

    /**
     * create_time
     */
    private Date createTime;

    /**
     * update_time
     */
    private Date updateTime;


}
