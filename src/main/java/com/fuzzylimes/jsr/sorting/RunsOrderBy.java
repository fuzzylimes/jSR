package com.fuzzylimes.jsr.sorting;

public enum RunsOrderBy {


    /** sorts by the game the run was done in */
    GAME("game"),
    /** sorts by the category the run was done in */
    CATEGORY("category"),
    /** sorts by the level the run was done in */
    LEVEL("level"),
    /** sorts by the console used for the run */
    PLATFORM("platform"),
    /** sorts by the console region the run was done in */
    REGION("region"),
    /** sorts by whether or not a run is done via emulator */
    EMULATED("emulated"),
    /** sorts by the date the run happened on */
    DATE("date"),
    /** sorts by the date when the run was submitted to speedrun.com */
    SUBMITTED("submitted"),
    /** sorts by verification status */
    STATUS("status"),
    /** sorts by the date the run was verified on */
    VERIFY_DATE("verify-date");

    private String orderBy;

    private RunsOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
