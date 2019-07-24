package com.gopoop.bd.tsc.spider;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @date 2019/7/17 18:53
 */
@Data
public class PipelineConfig {

    /**
     * insert_sql
     */
    private String insertSql;

    /**
     * table_sql
     */
    private String tableSql;
}
