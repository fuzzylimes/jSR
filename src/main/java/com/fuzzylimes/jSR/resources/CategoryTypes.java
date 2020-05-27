package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CategoryTypes {

    @JsonProperty("per-game")
    PER_GAME("global"),
    @JsonProperty("per-level")
    PER_LEVEL("full-game");

    private String type;

    private CategoryTypes(String type) {
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
