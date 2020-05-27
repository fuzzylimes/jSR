package com.fuzzylimes.jSR.queryParameters;

public enum CategoriesOrderBy {


    /** sorts alphanumerically by the category name */
    NAME("name"),
    /** sorts by miscellaneous flag */
    MISCELLANEOUS("miscellaneous"),
    /** uses the order as defined by the game moderator */
    POS("pos");

    private String orderBy;

    private CategoriesOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
