package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RunStatusTypes {

    @JsonProperty("new")
    NEW("new"),
    @JsonProperty("verified")
    VERIFIED("verified"),
    @JsonProperty("rejected")
    REJECTED("rejected");

    private String type;

    private RunStatusTypes(String type) {
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
