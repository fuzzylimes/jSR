package com.fuzzylimes.jsr.query_parameters.sorting;

/**
 * Supported fields to order by when performing a Users query.
 */
public enum UsersOrderBy implements OrderBy  {

    /** sorts alphanumerically by the international name */
    NAME_INT("name.int"),
    /** sorts alphanumerically by the japanese name */
    NAME_JP("name.jap"),
    /** sorts alphanumerically by the signup date */
    SIGNUP("signup"),
    /** sorts alphanumerically by the user role */
    ROLE("role");

    private String orderBy;

    UsersOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }
}
