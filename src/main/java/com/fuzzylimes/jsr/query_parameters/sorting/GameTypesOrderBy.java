package com.fuzzylimes.jsr.query_parameters.sorting;

public enum GameTypesOrderBy implements OrderBy  {


    /** sorts alphanumerically by the region name */
    NAME("name");

    private String orderBy;

    GameTypesOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
