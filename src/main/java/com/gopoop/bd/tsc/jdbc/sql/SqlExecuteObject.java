package com.gopoop.bd.tsc.jdbc.sql;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.gopoop.bd.tsc.common.constants.SQLContants;
import com.gopoop.bd.tsc.common.utils.StringUtils;
import lombok.Data;

import java.util.HashMap;
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
    private Map<String,Object> whereConditionMap;
    /**
     * 字段定义
     */
    private List<Field> fields;

    public SqlExecuteObject() {
    }

    public SqlExecuteObject(String tableName, Map<String, Object> fieldValueMap, Map<String, Object> whereConditionMap, List<Field> fields) {
        this.tableName = tableName;
        this.fieldValueMap = fieldValueMap;
        this.whereConditionMap = whereConditionMap;
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
        private Map<String,Object> whereConditionMap;
        /**
         * 字段定义
         */
        private List<Field> fields;


        public SqlExecuteObject.SqlExecuteObjectBuilder tableName(String tableName) {
            if(!tableName.contains(StringUtils.UNDERLINE)){
                this.tableName = StrUtil.toUnderlineCase(tableName);
            }
            if(this.tableName.endsWith(SQLContants.UNDERLINE_ENTITY)){
                this.tableName = this.tableName.replace(SQLContants.UNDERLINE_ENTITY, StringUtils.EMPTY);
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
            this.fieldValueMap = new HashMap<>();
            for (Map.Entry<String, Object> stringObjectEntry : temp.entrySet()) {
                if(stringObjectEntry.getKey().equals(SQLContants.ID)){
                    continue;
                }
                if(stringObjectEntry.getValue() instanceof String){
                    fieldValueMap.put(stringObjectEntry.getKey(), StringUtils.surround(String.valueOf(stringObjectEntry.getValue()),StringUtils.SINGLE_QUOTES));
                }else{
                    fieldValueMap.put(stringObjectEntry.getKey(),stringObjectEntry.getValue() != null ? stringObjectEntry.getValue() : StringUtils.NULL);
                }
            }
            return this;
        }

        public SqlExecuteObject.SqlExecuteObjectBuilder whereConditionMap(Map<String,Object> whereConditionMap) {
            this.whereConditionMap = whereConditionMap;
            return this;
        }

        public SqlExecuteObject.SqlExecuteObjectBuilder fields(List<Field> fields) {
            this.fields = fields;
            return this;
        }


        public SqlExecuteObject build() {
            return new SqlExecuteObject(this.tableName, this.fieldValueMap,this.whereConditionMap,this.fields);
        }
    }

}
