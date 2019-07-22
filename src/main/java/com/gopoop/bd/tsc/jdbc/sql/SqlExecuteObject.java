package com.gopoop.bd.tsc.jdbc.sql;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.gopoop.bd.tsc.common.utils.SqlUtil;
import com.gopoop.bd.tsc.common.utils.StringUtils;
import lombok.Data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author 郭速凯
 * @date 2019/7/19 14:41
 */
@Data
public class SqlExecuteObject {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 字段-值
     */
    private Map<String,Object> fieldValueMap;
    /**
     * 参数
     */
    private List<Condition> conditions;
    /**
     * 字段定义
     */
    private List<Field> fields;



    public SqlExecuteObject() {
    }

    public SqlExecuteObject(String tableName, Map<String, Object> fieldValueMap, List<Condition> conditions, List<Field> fields) {
        this.tableName = tableName;
        this.fieldValueMap = fieldValueMap;
        this.conditions = conditions;
        this.fields = fields;
    }

    public static SqlExecuteObject.SqlExecuteObjectBuilder builder() {
        return new SqlExecuteObject.SqlExecuteObjectBuilder();
    }

    public static class SqlExecuteObjectBuilder {
        /**
         * 表名
         */
        private String tableName;
        /**
         * 字段-值
         */
        private Map<String,Object> fieldValueMap;
        /**
         * 参数
         */
        private List<Condition> conditions;
        /**
         * 字段定义
         */
        private List<Field> fields;


        public SqlExecuteObject.SqlExecuteObjectBuilder tableName(String tableName) {
            if(!tableName.contains(StringUtils.UNDERLINE)){
                this.tableName = StrUtil.toUnderlineCase(tableName);
            }
            if(this.tableName.endsWith(SqlUtil.UNDERLINE_ENTITY)){
                this.tableName = this.tableName.replace(SqlUtil.UNDERLINE_ENTITY, StringUtils.EMPTY);
            }
            return this;
        }

        public SqlExecuteObject.SqlExecuteObjectBuilder fieldValueMap(Object object) {
            Map<String,Object> temp = null;
            if(object instanceof Map){
                temp = (Map<String,Object>)object;
            }else {
                temp = BeanUtil.beanToMap(object,Boolean.TRUE,Boolean.FALSE);
            }
            if((int)temp.get(SqlUtil.ID) != 0){
                temp.put(SqlUtil.UPDATE_TIME, DateUtil.now());
            }else{
                temp.put(SqlUtil.CREATE_TIME, DateUtil.now());
            }
            this.fieldValueMap = new HashMap<>();
            for (Map.Entry<String, Object> stringObjectEntry : temp.entrySet()) {
                if(stringObjectEntry.getValue() instanceof String){
                    fieldValueMap.put(stringObjectEntry.getKey(), StringUtils.surround(String.valueOf(stringObjectEntry.getValue()),StringUtils.SINGLE_QUOTES));
                } else{
                    fieldValueMap.put(stringObjectEntry.getKey(),stringObjectEntry.getValue() != null ? stringObjectEntry.getValue() : StringUtils.NULL);
                }
            }
            return this;
        }

        public SqlExecuteObject.SqlExecuteObjectBuilder conditions(List<Condition> conditions) {
            if(CollectionUtil.isNotEmpty(conditions)){
                this.conditions = new LinkedList<>();
                for (Condition condition : conditions) {
                    if(condition.getValue() instanceof String){
                        condition.setValue(StringUtils.surround((String)condition.getValue(),StringUtils.SINGLE_QUOTES));
                    }
                    this.conditions.add(condition);
                }
            }
            return this;
        }

        public SqlExecuteObject.SqlExecuteObjectBuilder fields(List<Field> fields) {
            this.fields = fields;
            return this;
        }


        public SqlExecuteObject build() {
            return new SqlExecuteObject(this.tableName, this.fieldValueMap,this.conditions,this.fields);
        }
    }

}
