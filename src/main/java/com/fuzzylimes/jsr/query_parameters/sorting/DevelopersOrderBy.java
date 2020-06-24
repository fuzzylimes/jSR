package com.fuzzylimes.jsr.query_parameters.sorting;

/**
 * Supported fields to order by when performing a Developers query.
 */
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
}
