package com.gopoop.bd.tsc.spider;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @date 2019/7/17 18:53
 */
@Data
@ApiModel
public class PipelineConfig {
    @ApiModelProperty(notes = "插入的sql")
    private String insertSql;
    @ApiModelProperty(notes = "建表的sql")
    private String tableSql;
}
