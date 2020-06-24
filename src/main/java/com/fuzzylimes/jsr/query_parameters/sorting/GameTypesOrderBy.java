package com.fuzzylimes.jsr.query_parameters.sorting;

/**
 * Supported fields to order by when performing a GameType query.
 */
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
}
