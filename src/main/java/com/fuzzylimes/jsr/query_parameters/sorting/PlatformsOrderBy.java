package com.fuzzylimes.jsr.query_parameters.sorting;

public enum PlatformsOrderBy implements OrderBy {


    /** sorts alphanumerically by the platform name */
    NAME("name"),
    /** sorts by the year the platform was released */
    RELEASED("released");

    private String orderBy;

    PlatformsOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
