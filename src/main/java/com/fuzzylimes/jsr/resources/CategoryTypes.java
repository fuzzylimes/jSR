package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Supported Game Categories
 */
public enum CategoryTypes {

    @JsonProperty("per-game")
    PER_GAME("per-game"),
    @JsonProperty("per-level")
    PER_LEVEL("per-level");

    private String type;

    CategoryTypes(String type) {
        this.type = type;
    }

    @JsonProperty
    public String getType() {
        return type;
    }
}
