package com.gopoop.bd.tsc.jdbc.sql.generator;


import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;

/**
 * @author 郭速凯
 * @date 2019/7/19 12:25
 */
public abstract class AbstractSqlGenerator implements SqlGenerator{



    protected abstract String getMainSql();

    protected abstract String getParamSql();

    @Override
    public String generate() {
        return this.getMainSql() + this.getParamSql();
    }
}
