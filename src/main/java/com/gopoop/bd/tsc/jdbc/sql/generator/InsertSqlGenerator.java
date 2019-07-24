package com.gopoop.bd.tsc.jdbc.sql.generator;

import com.gopoop.bd.tsc.common.utils.SqlUtil;
import com.gopoop.bd.tsc.common.utils.StringUtils;
import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;

import java.util.*;

/**
 *
 * @date 2019/7/19 12:25
 */
public class InsertSqlGenerator extends AbstractSqlGenerator{

    private SqlExecuteObject sqlExecuteObject;

    public InsertSqlGenerator(SqlExecuteObject sqlExecuteObject) {
        this.sqlExecuteObject = sqlExecuteObject;
    }


    @Override
    public String getMainSql() {
        List<String> fields = new LinkedList<>();
        List<Object> values = new LinkedList<>();
        for (Map.Entry<String, Object> stringObjectEntry : sqlExecuteObject.getFieldValueMap().entrySet()) {
            if(stringObjectEntry.getKey().equals(SqlUtil.ID)){
                continue;
            }
            fields.add(stringObjectEntry.getKey());
            values.add(stringObjectEntry.getValue());
        }
        StringBuffer sql = new StringBuffer(SqlUtil.INSERT_INTO);
        sql.append(sqlExecuteObject.getTableName() + "(");
        sql.append(StringUtils.join(",",fields));
        sql.append(") values (");
        sql.append(StringUtils.join(",",values));
        sql.append(")");
        return sql.toString();
    }

    @Override
    public String getWhereSql() {
        return StringUtils.EMPTY;
    }

    @Override
    protected String getLimitSql() {
        return StringUtils.EMPTY;
    }
}
