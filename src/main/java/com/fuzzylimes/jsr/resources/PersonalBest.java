package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PersonalBest {

    @JsonProperty("run")
    private Run run;

    @JsonProperty("place")
    private int place;

    @JsonProperty("game")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Game game;

    @JsonProperty("category")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Category category;

    @JsonProperty("level")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Level level;

    @JsonProperty("players")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User players;

    @JsonProperty("region")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Region region;

    @JsonProperty("platform")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Platform platform;

}
