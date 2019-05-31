package com.pinyougou.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by
 * kim on 2019/5/23.
 */
public class PageResult implements Serializable {

    private long total;//总记录数

    private List rows;//当前页面结果

    public PageResult(long total,List rows){

        this.total=total;
        this.rows=rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
