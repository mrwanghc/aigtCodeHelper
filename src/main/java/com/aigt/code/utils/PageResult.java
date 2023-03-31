package com.aigt.code.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */
public class PageResult<T extends Serializable> implements Serializable {
	private static final long serialVersionUID = -298782540975824948L;

	public PageResult() {
    }

    public PageResult(int page, int perPage, List<T> result, int totalNum) {
        this.page = page;
        this.perPage = perPage;
        this.result = result;
        this.totalNum = totalNum;
    }
    /**
     * 当前页码
     */
    private int page;

    /**
     * 每页条数
     */
    @JsonProperty(value = "per_page")
    private int perPage;

    /**
     * 需要展示的数据集合
     */
    private List<T> result ;

    /**
     *总条数
     */
    @JsonProperty(value = "total_num")
    private int totalNum;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
