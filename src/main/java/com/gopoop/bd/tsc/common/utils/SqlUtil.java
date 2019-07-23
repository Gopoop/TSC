package com.gopoop.bd.tsc.common.utils;

import com.gopoop.bd.tsc.jdbc.sql.Condition;
import com.gopoop.bd.tsc.jdbc.sql.PageParam;

public class SqlUtil {

    public static final String INSERT_INTO = "insert into ";
    public static final String UPDATE = "update ";
    public static final String SELECT = "select ";
    public static final String FROM = " from ";
    public static final String COUNT = " count(*) ";
    public static final String SET = " set  ";
    public static final String WHERE = " where  ";
    public static final String LIMIT = " limit  ";
    public static final String OFFSET = " offset  ";
    public static final String AND = " and  ";
    public static final String AS = " as  ";
    public static final String ID = "id";
    public static final String LIKE = " like ";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";
    public static final String GT = " > ";
    public static final String LT = " < ";
    public static final String GT_EQUALS = " >= ";
    public static final String LT_EQUALS = " <= ";

    public static final String whereSql(Condition condition){
        String comparator = StringUtils.EQUALS;
        if(condition.getCompare() != null){
            switch (condition.getCompare()){
                case GT:
                    comparator = GT;
                    break;
                case LT:
                    comparator = LT;
                    break;
                case GT_EQUALS:
                    comparator = GT_EQUALS;
                    break;
                case LT_EQUALS:
                    comparator = LT_EQUALS;
                    break;
                case LIKE:
                    comparator = LIKE;
                    condition.setValue(String.valueOf(condition.getValue()));
                    break;
                default:break;
            }
        }
        return SqlUtil.AND + condition.getField() + comparator + condition.getValue();
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

    public static String limitSql(PageParam pageParam) {
        return LIMIT + pageParam.getPageSize() + OFFSET + (pageParam.getPageNow() - 1) * 10;
    }

    public static String countHeadSql(String tableName) {
        return SELECT + COUNT + FROM + tableName;

    }
}
