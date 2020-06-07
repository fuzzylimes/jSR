package com.fuzzylimes.jsr.query_parameters.sorting;

public enum Direction {

    ASCCENDING("asc"),
    DESCENDING("desc");

    private String sortDirection;

    private Direction(String role) {
        this.sortDirection = role;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }
}
