package com.fuzzylimes.jsr.query_parameters.sorting;

public enum RegionsOrderBy implements OrderBy  {


    /** sorts alphanumerically by the region name */
    NAME("name");

    private String orderBy;

    RegionsOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
