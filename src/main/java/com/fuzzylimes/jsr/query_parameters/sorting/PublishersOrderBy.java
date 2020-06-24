package com.fuzzylimes.jsr.query_parameters.sorting;

/**
 * Supported fields to order by when performing a Publisher query.
 */
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
}
