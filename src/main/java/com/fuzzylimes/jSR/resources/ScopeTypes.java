package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ScopeTypes {

    @JsonProperty("global")
    GLOBAL("global"),
    @JsonProperty("full-game")
    FULL_GAME("full-game"),
    @JsonProperty("all-levels")
    ALL_LEVELS("all-levels"),
    @JsonProperty("single-level")
    SINGLE_LEVEL("single-level");

    private String type;

    private ScopeTypes(String type) {
        this.type = type;
    }

    @JsonProperty
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
