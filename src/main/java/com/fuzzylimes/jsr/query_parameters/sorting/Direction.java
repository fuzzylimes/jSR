package com.fuzzylimes.jsr.query_parameters.sorting;

/**
 * The direction in which sorted data should be returned. Operation will be performed on the specified "OrderBy" field,
 * otherwise the default will be used.
 */
public enum Direction {

    ASCCENDING("asc"),
    DESCENDING("desc");

    private String sortDirection;

    Direction(String role) {
        this.sortDirection = role;
    }

    public String getSortDirection() {
        return sortDirection;
    }
}
