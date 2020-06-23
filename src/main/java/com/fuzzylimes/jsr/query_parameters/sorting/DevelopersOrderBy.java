package com.fuzzylimes.jsr.query_parameters.sorting;

public enum DevelopersOrderBy implements OrderBy  {


    /** sorts alphanumerically by the region name */
    NAME("name");

    private String orderBy;

    DevelopersOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
