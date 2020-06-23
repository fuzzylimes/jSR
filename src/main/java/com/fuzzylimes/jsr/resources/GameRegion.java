package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class GameRegion {

    @JsonProperty("ids")
    private List<String> ids;

    @JsonProperty("embeddedRegions")
    private List<Region> embeddedRegions;

}

