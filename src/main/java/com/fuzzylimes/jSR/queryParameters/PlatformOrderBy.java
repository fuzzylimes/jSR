package com.fuzzylimes.jSR.queryParameters;

public enum PlatformOrderBy {


    /** sorts alphanumerically by the platform name */
    NAME("name"),
    /** sorts by the year the platform was released */
    RELEASED("released");

    private String orderBy;

    private PlatformOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
