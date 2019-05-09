package com.rayvision.rpc.common.pagination;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Hugo.Wwg
 * @Since 2017-08-21
 */
public final class PageBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int DEFAULT_PAGE_SIZE = 10;

    // 每页显示多少条记录，固定值，可以从配置文件中获取
    private int pageMaxSize;

    // 当前页码，传进来
    private int pageIndex;

    // 每页的起始位置，通过当前页面和每页显示多少条记录算出来的
    private int offset;

    // 总记录数，查询出来的，传进来
    private int count;

    // 每页显示的数据,查询出来的，传进来
    private List<T> list;

    // 总页数，计算出来，通过总记录数和每页显示多少条记录算出来的
    private int pageCount;

    private PageBean() {
    }

    private PageBean(int pageIndex, int pageMaxSize) {
        this(pageIndex, pageMaxSize, 0);
    }

    private PageBean(int pageIndex, int pageMaxSize, int count) {
        this(pageIndex, pageMaxSize, count, null);
    }

    private PageBean(int pageIndex, int pageMaxSize, int count, List<T> list) {
        this.setPageMaxSize(pageMaxSize);
        this.setCount(count);
        this.setPageIndex(pageIndex);
        this.setList(list);
    }

    public void setPageMaxSize(int pageMaxSize) {
        this.pageMaxSize = pageMaxSize <= 0 ? DEFAULT_PAGE_SIZE : pageMaxSize;
    }

    public int getPageMaxSize() {
        return pageMaxSize;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex < 1 ? 1 : pageIndex;
        this.setOffset((this.pageIndex - 1) * pageMaxSize);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setCount(int count) {
        this.count = count;
        this.setPageCount(count % pageMaxSize == 0 ? count / pageMaxSize : count / pageMaxSize + 1);
    }

    public int getCount() {
        return count;
    }

    //设置数据库偏移索引,如果偏移量为负数则数据库会出错
    public void setOffset(int offset) {
        this.offset = offset < 0 ? 0 : offset;
    }

    public int getOffset() {
        return offset;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public static <T> PageBean<T> create(int pageIndex, int pageMaxSize) {
        return new PageBean<>(pageIndex, pageMaxSize);
    }

    public static <T> PageBean<T> create(int pageIndex, int pageMaxSize, int count) {
        return new PageBean<>(pageIndex, pageMaxSize, count);
    }

    public static <T> PageBean<T> create(int pageIndex, int pageMaxSize, int count, List<T> list) {
        return new PageBean<>(pageIndex, pageMaxSize, count, list);
    }


}