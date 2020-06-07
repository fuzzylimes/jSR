package com.fuzzylimes.jsr.sorting;

public enum VariablesOrderBy {


    /** sorts alphanumerically by the variable name */
    NAME("name"),
    /** sorts by mandatory flag */
    MANDATORY("mandatory"),
    /** sorts by user-defined flag */
    USER_DEFINED("user-defined"),
    /** uses the order as defined by the game moderator */
    POS("pos");

    private String orderBy;

    private VariablesOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
