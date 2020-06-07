package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VariableValuesValue {
    @JsonProperty("flags")
    private Flags flags;

    @JsonProperty("rules")
    private String rules;

    @JsonProperty("label")
    private String label;
}
