package com.gopoop.bd.tsc.jdbc.sql.generator;

import cn.hutool.core.date.DateUtil;
import com.gopoop.bd.tsc.common.utils.SqlUtil;
import com.gopoop.bd.tsc.common.utils.StringUtils;
import com.gopoop.bd.tsc.jdbc.sql.Condition;
import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;

import java.util.Date;
import java.util.Map;

/**
 * @author 郭速凯
 * @date 2019/7/19 12:25
 */
public class UpdateSqlGenerator extends AbstractSqlGenerator{

    private SqlExecuteObject sqlExecuteObject;

    public UpdateSqlGenerator(SqlExecuteObject sqlExecuteObject) {
        this.sqlExecuteObject = sqlExecuteObject;
    }

    @Override
    public String getMainSql() {
        StringBuffer sql = new StringBuffer(SqlUtil.updateHeadSql(sqlExecuteObject.getTableName()));
        for (Map.Entry<String, Object> stringObjectEntry : sqlExecuteObject.getFieldValueMap().entrySet()) {
            if(stringObjectEntry.getKey().equals(SqlUtil.ID) || stringObjectEntry.getKey().equals(SqlUtil.CREATE_TIME)){
                continue;
            }
            sql.append(SqlUtil.equalsSql(stringObjectEntry.getKey(),stringObjectEntry.getValue()));
        }
        sql.delete(sql.length()-1,sql.length());
        return sql.toString();
    }

    @Override
    public String getWhereSql() {
        for (Map.Entry<String, Object> stringObjectEntry : sqlExecuteObject.getFieldValueMap().entrySet()) {
            if(stringObjectEntry.getKey().equals(SqlUtil.ID)){
                return SqlUtil.WHERE + "1 = 1 " + SqlUtil.whereSql(Condition.builder().field(stringObjectEntry.getKey()).value(stringObjectEntry.getValue()).build());
            }
        }
        return StringUtils.EMPTY;
    }

    @Override
    protected String getLimitSql() {
        return StringUtils.EMPTY;
    }

}
