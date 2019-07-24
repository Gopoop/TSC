package com.gopoop.bd.tsc.jdbc.sql;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.gopoop.bd.tsc.common.utils.SqlUtil;
import com.gopoop.bd.tsc.common.utils.StringUtils;
import lombok.Data;

import java.util.*;

/**
 *
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
    /**
     * 分页参数
     */
    private PageParam pageParam;



    public SqlExecuteObject() {
    }

    public SqlExecuteObject(String tableName, Map<String, Object> fieldValueMap, List<Condition> conditions, List<Field> fields,PageParam pageParam) {
        this.tableName = tableName;
        this.fieldValueMap = fieldValueMap;
        this.conditions = conditions;
        this.fields = fields;
        this.pageParam = pageParam;
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
        /**
         * 分页参数
         */
        private PageParam pageParam;


        public SqlExecuteObject.SqlExecuteObjectBuilder tableName(String tableName) {
            this.tableName = tableName;
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

        public SqlExecuteObject.SqlExecuteObjectBuilder condition(Condition condition){
            this.initConditions();
            this.conditions.add(condition);
            return this;
        }



        public SqlExecuteObject.SqlExecuteObjectBuilder conditions(Object req) throws IllegalAccessException {
            if(req != null){
                this.initConditions();
                java.lang.reflect.Field[] fields = ClassUtil.getDeclaredFields(req.getClass());

                for (java.lang.reflect.Field field : fields) {
                    field.setAccessible(Boolean.TRUE);
                    Object value = field.get(req);
                    if(value == null){
                        continue;
                    }
                    Condition.ConditionBuilder builder = Condition.builder();
                    String fieldName = StringUtils.toUnderlineCase(field.getName());
                    builder.field(fieldName);
                    //string类型统一使用like查询
                    if(field.getType() == String.class){
                        builder.value(StringUtils.surround(StringUtils.surround((String)value,StringUtils.PERCENT),StringUtils.SINGLE_QUOTES))
                                .compare(Compare.LIKE);
                    }
                    // date类型  时间区间
                    else if(field.getType() == Date.class && fieldName.startsWith("start_")){
                        builder.field(StringUtils.replace(fieldName,"start_",StringUtils.EMPTY))
                                .value(StringUtils.surround(DateUtil.format((Date)value, DatePattern.NORM_DATETIME_PATTERN),StringUtils.SINGLE_QUOTES))
                                .compare(Compare.GT_EQUALS);
                    }else if(field.getType() == Date.class && fieldName.startsWith("end_")){
                        builder.field(StringUtils.replace(fieldName,"end_",StringUtils.EMPTY))
                                .value(StringUtils.surround(DateUtil.format((Date)value, DatePattern.NORM_DATETIME_PATTERN),StringUtils.SINGLE_QUOTES))
                                .compare(Compare.LT_EQUALS);
                    }
                    //基础类型
                    else{
                        builder.value(field.get(req)).compare(Compare.EQUALS);
                    }
                    this.conditions.add(builder.build());
                }
            }
            return this;
        }

        public SqlExecuteObject.SqlExecuteObjectBuilder fields(List<Field> fields) {
            this.fields = fields;
            return this;
        }

        public SqlExecuteObject.SqlExecuteObjectBuilder pageParam(PageParam pageParam) {
            this.pageParam = pageParam;
            return this;
        }


        public SqlExecuteObject build() {
            return new SqlExecuteObject(this.tableName, this.fieldValueMap,this.conditions,this.fields,this.pageParam);
        }

        private void initConditions() {
            if(this.conditions == null){
                this.conditions = new LinkedList<>();
            }
        }
    }

}
