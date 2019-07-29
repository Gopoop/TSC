package com.gopoop.bd.tsc.jdbc.sql.generator;

import com.gopoop.bd.tsc.common.utils.SqlUtil;
import com.gopoop.bd.tsc.common.utils.StringUtils;
import com.gopoop.bd.tsc.jdbc.sql.Condition;
import com.gopoop.bd.tsc.jdbc.sql.Field;
import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;

import java.util.Map;

/**
 *
 * @date 2019/7/19 12:25
 */
public class CreateTableSqlGenerator extends AbstractSqlGenerator{

    private SqlExecuteObject sqlExecuteObject;

    public CreateTableSqlGenerator(SqlExecuteObject sqlExecuteObject) {
        this.sqlExecuteObject = sqlExecuteObject;
    }

    @Override
    public String getMainSql() {
        StringBuffer sql = new StringBuffer(SqlUtil.createTableHeadSql(sqlExecuteObject.getTableName()) + StringUtils.OPEN_PARENTHESES);
        for (Field field : sqlExecuteObject.getFields()) {
            sql.append(SqlUtil.fieldSql(field.getName(),field.getType(),field.getLength()));
        }
        sql.delete(sql.length()-1,sql.length());
        sql.append(StringUtils.CLOSE_PARENTHESES);
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
