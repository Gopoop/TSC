package com.gopoop.bd.tsc.common.utils;

public class SqlUtil {

    public static final String INSERT_INTO = "insert into ";
    public static final String UPDATE = "update ";
    public static final String SELECT = "select ";
    public static final String FROM = " from ";
    public static final String SET = " set  ";
    public static final String WHERE = " where  ";
    public static final String AND = " and  ";
    public static final String AS = " as  ";
    public static final String ID = "id";
    public static final String UNDERLINE_ENTITY = "_entity";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";


    public static final String whereSql(String field,Object value){
       return WHERE + field + StringUtils.EQUALS + value;
    }

    public static final String equalsSql(String field,Object value){
        return StringUtils.SPACE + field + StringUtils.EQUALS + value + StringUtils.COMMA ;
    }


    public static final String updateHeadSql(String tableName){
        return UPDATE + tableName + SET ;
    }

    public static final String selectFieldSql(String fieldName,String alias){
        return fieldName + AS + alias + StringUtils.COMMA ;
    }
}
