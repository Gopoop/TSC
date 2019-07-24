package com.gopoop.bd.tsc.spider;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 郭速凯
 * @date 2019/7/17 18:54
 */
@Data
public class PageProcessConfig{

    /**
     * 提取规则  策略列表 json存储
     */
    private String rule;

    /**
     * 属性名称
     */
    private String attribute;

    /**
     * 持久化字段名
     */
    private String fieldEn;

    /**
     * 字段中文名，用户界面展示
     */
    private String fieldCn;

    /**
     * 字段长度
     */
    private Integer fieldLength;

    /**
     * 字段类型
     */
    private String fieldType;

    /**
     * 字段是否展示
     */
    private Integer fieldIsShow;

    /**
     * 页面跳过规则  策略列表 json存储
     */
    private String skipCondition;

    /**
     * 后续抓取url
     */
    private String targetRequestsRegex;

}
