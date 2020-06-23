package com.fuzzylimes.jsr.query_parameters.sorting;

public enum LevelsOrderBy implements OrderBy {


    /** sorts alphanumerically by the category name */
    NAME("name"),
    /** uses the order as defined by the game moderator */
    POS("pos");

    private String orderBy;

    LevelsOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
