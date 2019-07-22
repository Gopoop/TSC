package com.gopoop.bd.tsc.jdbc.sql.generator;

import cn.hutool.core.collection.CollectionUtil;
import com.gopoop.bd.tsc.common.utils.SqlUtil;
import com.gopoop.bd.tsc.common.utils.StringUtils;
import com.gopoop.bd.tsc.jdbc.sql.Condition;
import com.gopoop.bd.tsc.jdbc.sql.Field;
import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;

import java.util.Map;


/**
 * @author 郭速凯
 * @date 2019/7/19 12:25
 */
public class SelectSqlGenerator extends AbstractSqlGenerator{

    private SqlExecuteObject sqlExecuteObject;

    public SelectSqlGenerator(SqlExecuteObject sqlExecuteObject) {
        this.sqlExecuteObject = sqlExecuteObject;
    }


    @Override
    public String getMainSql() {
        StringBuffer sql = new StringBuffer(SqlUtil.SELECT);
        if(CollectionUtil.isEmpty(sqlExecuteObject.getFields())){
            sql.append("*");
        }else{
            for (Field field : sqlExecuteObject.getFields()) {
                if(field.getIsShow()){
                    sql.append(SqlUtil.selectFieldSql(field.getName(),field.getAlias()));
                }
            }
        }
        sql.delete(sql.length()-1,sql.length());
        sql.append(SqlUtil.FROM + sqlExecuteObject.getTableName());
        return sql.toString();
    }

    @Override
    public String getParamSql() {
        if(CollectionUtil.isNotEmpty(sqlExecuteObject.getConditions())){
            StringBuffer whereSql = new StringBuffer(StringUtils.SPACE + "1 = 1 ");
            for (Condition condition : sqlExecuteObject.getConditions()) {
                whereSql.append(SqlUtil.whereSql(condition));
            }
        }
        return StringUtils.EMPTY;
    }

}
