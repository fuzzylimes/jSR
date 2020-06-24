package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The times that may be submitted for a run
 */
public enum RunTimeTypes {

    @JsonProperty("realtime")
    REALTIME("realtime"),
    @JsonProperty("realtime_noloads")
    REALTIME_NOLOADS("realtime_noloads"),
    @JsonProperty("ingame")
    INGAME("ingame");

    private String type;

    RunTimeTypes(String type) {
        this.type = type;
    }

    @JsonProperty
    public String getType() {
        return type;
    }
}
