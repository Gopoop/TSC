package com.gopoop.bd.tsc.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 郭速凯
 * @date 2019/7/17 18:53
 */
@Data
public class PipelineEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * spider_id
     */
    private Integer spiderId;

    /**
     * insert_sql
     */
    private String insertSql;

    /**
     * table_sql
     */
    private String tableSql;
}
