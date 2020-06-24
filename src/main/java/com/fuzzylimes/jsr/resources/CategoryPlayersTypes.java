package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Supported types of Players for a Category
 */
public enum CategoryPlayersTypes {

    @JsonProperty("exactly")
    EXACTLY("exactly"),
    @JsonProperty("up-to")
    UP_TO("up-to");

    private String type;

    CategoryPlayersTypes(String type) {
        this.type = type;
    }

    @JsonProperty
    public String getType() {
        return type;
    }
}
