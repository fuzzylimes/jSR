package com.fuzzylimes.jsr.query_parameters.sorting;

public enum PublishersOrderBy implements OrderBy  {


    /** sorts alphanumerically by the region name */
    NAME("name");

    private String orderBy;

    PublishersOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
