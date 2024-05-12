package com.hjong.aifilterbox.entity;

import lombok.Data;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/11
 **/
@Data
public class Page<T> {
    //每页显示条数
    private int pageSize;
    //总条数
    private int totalCount;
    //当前页数
    private int currentPage;
    //总页数
    private int totalPages;

    private List<T> list;
}
