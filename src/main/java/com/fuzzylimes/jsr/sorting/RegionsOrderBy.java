package com.fuzzylimes.jsr.sorting;

public enum RegionsOrderBy {


    /** sorts alphanumerically by the region name */
    NAME("name");

    private String orderBy;

    private RegionsOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
