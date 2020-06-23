package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CategoryTypes {

    @JsonProperty("per-game")
    PER_GAME("global"),
    @JsonProperty("per-level")
    PER_LEVEL("full-game");

    private String type;

    CategoryTypes(String type) {
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
