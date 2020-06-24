package com.fuzzylimes.jsr.query_parameters.sorting;

/**
 * Supported fields to order by when performing an Engines query.
 */
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
}
