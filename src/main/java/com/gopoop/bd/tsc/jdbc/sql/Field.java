package com.gopoop.bd.tsc.jdbc.sql;

import lombok.Data;

/**
 *
 * @date 2019/7/19 12:10
 */
@Data
public class Field {

    private String name;
    private String type;
    private Integer length;
    private Boolean isShow;
    private String alias;
}
