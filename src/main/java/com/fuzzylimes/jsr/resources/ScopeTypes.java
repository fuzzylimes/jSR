package com.fuzzylimes.jsr.resources;

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

    ScopeTypes(String type) {
        this.type = type;
    }

    @JsonProperty
    public String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
    }
}
