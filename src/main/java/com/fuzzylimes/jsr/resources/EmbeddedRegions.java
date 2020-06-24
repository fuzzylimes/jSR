package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


/**
 * Container for deserializing Regions inside another resource
 */
@Data
public class EmbeddedRegions {

    /** representation as a list of ids */
    @JsonProperty("ids")
    private List<String> ids;

    /** representation as list of objects when embedding enabled */
    @JsonProperty("embedded")
    private List<Region> embedded;

}

