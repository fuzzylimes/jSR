package com.fuzzylimes.jsr.query_parameters.sorting;

public enum EnginesOrderBy implements OrderBy  {


    /** sorts alphanumerically by the region name */
    NAME("name");

    private String orderBy;

    EnginesOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
