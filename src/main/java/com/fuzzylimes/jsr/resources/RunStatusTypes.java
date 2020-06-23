package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RunStatusTypes {

    @JsonProperty("new")
    NEW("new"),
    @JsonProperty("verified")
    VERIFIED("verified"),
    @JsonProperty("rejected")
    REJECTED("rejected");

    private String type;

    RunStatusTypes(String type) {
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
