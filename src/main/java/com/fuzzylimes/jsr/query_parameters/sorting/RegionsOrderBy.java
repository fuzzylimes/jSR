package com.fuzzylimes.jsr.query_parameters.sorting;

/**
 * Supported fields to order by when performing a Regions query.
 */
public enum RegionsOrderBy implements OrderBy  {

    /** sorts alphanumerically by the region name */
    NAME("name");

    private String orderBy;

    RegionsOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }
}
