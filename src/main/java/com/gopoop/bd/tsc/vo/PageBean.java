package com.gopoop.bd.tsc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 分页返回类
 * @author: 郭速凯
 * @create: 2019/6/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    private List<T> list;
    private Integer total;
    private Integer pageNow;
    private Integer pageSize;
}
