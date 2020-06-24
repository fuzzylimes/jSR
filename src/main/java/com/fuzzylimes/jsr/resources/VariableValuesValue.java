package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Definition for a variable
 */
@Data
public class VariableValuesValue {
    /** settings for variable */
    @JsonProperty("flags")
    private Flags flags;

    /** ruleset for variable */
    @JsonProperty("rules")
    private String rules;

    /** label for variable */
    @JsonProperty("label")
    private String label;
}
