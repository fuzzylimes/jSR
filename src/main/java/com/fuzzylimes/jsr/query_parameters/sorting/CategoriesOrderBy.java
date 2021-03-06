package com.fuzzylimes.jsr.query_parameters.sorting;

/**
 * Supported fields to order by when performing a Category query.
 */
public enum CategoriesOrderBy implements OrderBy {

    /** sorts alphanumerically by the category name */
    NAME("name"),
    /** sorts by miscellaneous flag */
    MISCELLANEOUS("miscellaneous"),
    /** uses the order as defined by the game moderator */
    POS("pos");

    private String orderBy;

    CategoriesOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }
}
