package com.gopoop.bd.tsc.spider.pipeline;

import com.gopoop.bd.tsc.jdbc.sql.Field;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 持久化配置
 * @date 2019/7/17 18:53
 */
@Data
@ApiModel
public class PipelineConfig {
    @ApiModelProperty(notes = "插入的sql")
    private String insertSql;
    @ApiModelProperty(notes = "建表的sql")
    private String tableSql;
    @ApiModelProperty(notes = "属性")
    private List<Field> fields;
    @ApiModelProperty(notes = "持久化方式")
    private PersistentWay persistentWay;
}
