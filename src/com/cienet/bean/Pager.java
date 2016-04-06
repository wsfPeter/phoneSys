package com.cienet.bean;

import java.util.List;

/**
 * @ClassName: Pager
 * @Description: 分页Bean
 * @author zhujiang
 * @date 2014年7月1日 下午11:17:16
 * 
 */
public class Pager {

    // 排序方式
    public enum OrderType {
        asc, desc
    }

    /**
     * @Fields MAX_PAGE_SIZE : 每页最大记录数限制
     */
    public static final Integer MAX_PAGE_SIZE = 500;

    /**
     * @Fields pageNumber : 当前页码
     */
    private Integer pageNumber = 1;

    /**
     * @Fields pageSize : 每页记录数
     */
    private Integer pageSize = 10;

    /**
     * @Fields totalCount : 总记录数
     */
    private Long totalCount = 0L;

    /**
     * @Fields totalPages : 总页数
     */
    private Long totalPages = 0L;

    /**
     * @Fields searchValue : 查找关键字
     */
    private String searchValue = "";

    /**
     * @Fields searchProperty : 查找属性名
     */
    private String searchProperty = "";

    /**
     * @Fields orderProperty : 排序字段
     */
    private String orderProperty = "id";

    /**
     * @Fields orderType : 排序方式
     */
    private OrderType orderType = OrderType.desc;

    /**
     * @Fields result : 数据List
     */
    @SuppressWarnings("rawtypes")
    private List result;//

    public String getOrderProperty() {
        return orderProperty;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    @SuppressWarnings("rawtypes")
    public List getResult() {
        return result;
    }

    public String getSearchProperty() {
        return searchProperty;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public Long getTotalPages() {
        totalPages = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            totalPages++;
        }
        return totalPages;
    }

    public void setOrderProperty(String orderProperty) {
        this.orderProperty = orderProperty;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public void setPageNumber(Integer pageNumber) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        this.pageNumber = pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize < 1) {
            pageSize = 1;
        } else if (pageSize > MAX_PAGE_SIZE) {
            pageSize = MAX_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    @SuppressWarnings("rawtypes")
    public void setResult(List result) {
        this.result = result;
    }

    public void setSearchProperty(String searchProperty) {
        this.searchProperty = searchProperty;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

}