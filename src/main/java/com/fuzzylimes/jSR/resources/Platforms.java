package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Platforms {

    @JsonProperty("platformList")
    @JsonAlias("data")
    private List<Platform> platformList;

    @JsonProperty("pagination")
    private Pagination pagination;
}
