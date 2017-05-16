package com.dudu.weixin.mould;

import com.dudu.soa.framework.dudusoahelp.DuduResponse;
import com.dudu.soa.framework.dudusoahelp.DuduSOAHelp;

import java.util.List;


/**
 * 返回的分页数据
 *
 * @param <T> Created by lizhen on 2017-05-16.
 */
public class PageResult<T> {
    /**
     * 调用service接口返回的list数据
     */
    private List<T> rows;
    /**
     * 页码
     */
    private Integer page;
    /**
     * 每页条数
     */
    private Integer pageSize;
    /**
     * 总页数
     */
    private Integer total;
    /**
     * 总条数
     */
    private Long records;

    public PageResult(List<T> rows) {
        DuduResponse pageResult = DuduSOAHelp.getResultCurrendDuduResponse();
        this.setPage(pageResult.getPageParams().getCurrentPage());
        this.setPageSize(pageResult.getPageParams().getPageSize());
        this.setTotal(pageResult.getPageParams().getPages());
        this.setRecords(pageResult.getPageParams().getTotal());
        this.rows = rows;
    }


    /**
     * PageResult(返回分页数据) 字符串形式
     *
     * @return PageResult(返回分页数据)字符串
     */
    @Override
    public String toString() {
        return "rows:" + rows + ",page:" + page + ",pageSize:" + pageSize + ",total:" + total + ",records:" + records;
    }

    /**
     * 获取 调用service接口返回的list数据
     *
     * @return rows 调用service接口返回的list数据
     */
    public List<T> getRows() {
        return this.rows;
    }

    /**
     * 设置 调用service接口返回的list数据
     *
     * @param rows 调用service接口返回的list数据
     * @return 返回 PageResult(返回分页数据)
     */
    public PageResult setRows(List<T> rows) {
        this.rows = rows;
        return this;
    }

    /**
     * 获取 页码
     *
     * @return page 页码
     */
    public Integer getPage() {
        return this.page;
    }

    /**
     * 设置 页码
     *
     * @param page 页码
     * @return 返回 PageResult(返回分页数据)
     */
    public PageResult setPage(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 获取 每页条数
     *
     * @return pageSize 每页条数
     */
    public Integer getPageSize() {
        return this.pageSize;
    }

    /**
     * 设置 每页条数
     *
     * @param pageSize 每页条数
     * @return 返回 PageResult(返回分页数据)
     */
    public PageResult setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 获取 总页数
     *
     * @return total 总页数
     */
    public Integer getTotal() {
        return this.total;
    }

    /**
     * 设置 总页数
     *
     * @param total 总页数
     * @return 返回 PageResult(返回分页数据)
     */
    public PageResult setTotal(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * 获取 总条数
     *
     * @return records 总条数
     */
    public Long getRecords() {
        return this.records;
    }

    /**
     * 设置 总条数
     *
     * @param records 总条数
     * @return 返回 PageResult(返回分页数据)
     */
    public PageResult setRecords(Long records) {
        this.records = records;
        return this;
    }
}
