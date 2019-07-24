package com.gopoop.bd.tsc.jdbc.sql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @date 2019/7/22 10:59
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Condition {
    private String field;
    private Object value;
    private Compare compare;
}
