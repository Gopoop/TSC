package com.gopoop.bd.tsc.entity;

import java.io.Serializable;
import lombok.Data;
import java.util.Date;

/**
 *  爬虫配置
 * @author gopoop 2019-07-17
 */
@Data
public class SpiderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 线程数 默认5
     */
    private Integer thread;

    /**
     * 初始的url
     */
    private String url;

    /**
     * 测试网页
     */
    private String testUrl;

    /**
     * 数据来源 1 html 2 ajax
     */
    private Integer dataSource;

    /**
     * 启动类型 1 同步启动 2异步启动
     */
    private Integer startType;

    /**
     * 持久化方式 1 mysql 2 json文件
     */
    private Integer persistPattern;

    /**
     * 状态 0 初始化 1 运行中 2停止
     */
    private Integer stat;

    /**
     * create_time
     */
    private Date createTime;

    /**
     * update_time
     */
    private Date updateTime;


}