package com.gopoop.bd.tsc.common.utils;

import cn.hutool.core.util.StrUtil;

/**
 * @author 郭速凯
 * @date 2019/7/19 17:34
 */
public class StringUtils extends StrUtil {

    public static final String SINGLE_QUOTES = "'";
    public static final String EQUALS = " = ";

    public static final String surround(String source,String surroundStr){
        return surroundStr + source + surroundStr;
    }
}
