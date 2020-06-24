package com.fuzzylimes.jsr.query_parameters.sorting;

/**
 * Supported fields to order by when performing a Series query.
 */
public enum SeriesOrderBy implements OrderBy  {

    /** sorts alphanumerically by the international name */
    NAME_INT("name.int"),
    /** sorts alphanumerically by the japanese name */
    NAME_JP("name.jap"),
    /** sorts alphanumerically by the signup date */
    ABBREVIATION("abbreviation"),
    /** sorts alphanumerically by the user role */
    CREATED("created");

    private String orderBy;

    SeriesOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }
}
