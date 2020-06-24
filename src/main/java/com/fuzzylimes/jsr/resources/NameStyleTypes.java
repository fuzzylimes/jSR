package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Names can be represented either by a single solid color, or by a gradient of two colors
 */
public enum NameStyleTypes {

    @JsonProperty("solid")
    SOLID("solid"),
    @JsonProperty("gradient")
    GRADIENT("gradient");

    private String type;

    NameStyleTypes(String type) {
        this.type = type;
    }

    @JsonProperty
    public String getType() {
        return type;
    }
}
