package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserNames {
    @JsonProperty("japanese")
    private String japanese;

    @JsonProperty("international")
    private String international;
}
