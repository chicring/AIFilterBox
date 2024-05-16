package com.hjong.aifilterbox.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/11
 **/
@NoArgsConstructor
@Data
public class PageResp<T> {

    public PageResp(Page<T> page){
        this.pageSize = page.getSize();
        this.totalCount = page.getTotal();
        this.currentPage = page.getCurrent();
        this.totalPages = page.getPages();
        this.list = page.getRecords();
    }

    //每页显示条数
    private long pageSize;
    //总条数
    private long totalCount;
    //当前页数
    private long currentPage;
    //总页数
    private long totalPages;

    private List<T> list;
}
