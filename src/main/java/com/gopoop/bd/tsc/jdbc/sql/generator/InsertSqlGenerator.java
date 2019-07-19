package com.gopoop.bd.tsc.jdbc.sql.generator;

import com.gopoop.bd.tsc.common.constants.SQLContants;
import com.gopoop.bd.tsc.entity.PageProcessEntity;
import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author 郭速凯
 * @date 2019/7/19 12:25
 */
public class InsertSqlGenerator extends AbstractSqlGenerator{


    @Override
    public String getMainSql(SqlExecuteObject sqlExecuteObject) {

        List<String> fields = new LinkedList<>();
        List<Object> values = new LinkedList<>();
        for (Map.Entry<String, Object> stringObjectEntry : sqlExecuteObject.getFieldValueMap().entrySet()) {
            if(stringObjectEntry.getKey().equals(SQLContants.ID)){
                continue;
            }
            fields.add(stringObjectEntry.getKey());
            values.add(stringObjectEntry.getValue());
        }
        StringBuffer sql = new StringBuffer(SQLContants.INSERT_INTO);
        sql.append(sqlExecuteObject.getTableName() + "(");
        sql.append(StringUtils.join(fields,","));
        sql.append(") values (");
        sql.append(StringUtils.join(values,","));
        sql.append(")");
        return sql.toString();
    }

    @Override
    public String getParamSql(SqlExecuteObject sqlExecuteObject) {
        return StringUtils.EMPTY;
    }

}
