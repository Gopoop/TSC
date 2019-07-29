package com.gopoop.bd.tsc.common.utils;

import cn.hutool.core.util.StrUtil;

/**
 *
 * @date 2019/7/19 17:34
 */
public class StringUtils extends StrUtil {

    public static final String SINGLE_QUOTES = "'";
    public static final String PERCENT = "%";
    public static final String EQUALS = " = ";
    public static final String OPEN_PARENTHESES = " ( ";
    public static final String CLOSE_PARENTHESES = " ) ";



    public static final String surround(String source,String surroundStr){
        return surroundStr + source + surroundStr;
    }
}
