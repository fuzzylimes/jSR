package com.fuzzylimes.jsr.query_parameters.sorting;

/**
 * Supported fields to order by when performing a Variables query.
 */
public enum VariablesOrderBy implements OrderBy {

    /** sorts alphanumerically by the variable name */
    NAME("name"),
    /** sorts by mandatory flag */
    MANDATORY("mandatory"),
    /** sorts by user-defined flag */
    USER_DEFINED("user-defined"),
    /** uses the order as defined by the game moderator */
    POS("pos");

    private String orderBy;

    VariablesOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }
}
