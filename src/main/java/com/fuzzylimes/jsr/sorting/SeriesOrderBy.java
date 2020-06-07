package com.fuzzylimes.jsr.sorting;

public enum SeriesOrderBy {


    /** sorts alphanumerically by the international name */
    NAME_INT("name.int"),
    /** sorts alphanumerically by the japanese name */
    NAME_JP("name.jap"),
    /** sorts alphanumerically by the signup date */
    ABBREVIATION("abbreviation"),
    /** sorts alphanumerically by the user role */
    CREATED("created");

    private String orderBy;

    private SeriesOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
