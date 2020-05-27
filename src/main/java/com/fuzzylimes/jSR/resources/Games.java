package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Games {

    @JsonProperty("gamesList")
    @JsonAlias("data")
    private List<Game> gamesList;

    @JsonProperty("pagination")
    private Pagination pagination;

}
