package com.fuzzylimes.jsr.query_parameters.sorting;

public enum GamesOrderBy implements OrderBy  {


    /** sorts alphanumerically by the international name */
    NAME_INT("name.int"),
    /** sorts alphanumerically by the japanese name */
    NAME_JP("name.jap"),
    /** sorts alphanumerically by the abbreviation */
    ABBREVIATION("abbreviation"),
    /** sorts by the release date */
    RELEASED("released"),
    /** sorts by the date when the game was added on speedrun.com */
    CREATED("created"),
    /** sorts by string similarity; only available when searching games by name; default when searching by name */
    SIMILARITY("similarity");

    private String orderBy;

    GamesOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
