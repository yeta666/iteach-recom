package com.recom.model;

/**
 * @param page 当前页码
 * @param data 页面数据
 *
 */
public class PageData {
	
    private int page;
    private Object data;

    public PageData(int page, Object data) {
        super();
        this.page = page;
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
