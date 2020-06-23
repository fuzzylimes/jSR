package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fuzzylimes.jsr.util.*;
import lombok.Data;

@Data
public class PersonalBest {

    @JsonProperty("run")
    private Run run;

    @JsonProperty("place")
    private int place;

    @JsonProperty("game")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonDeserialize(using = EmbeddedGameDeserializer.class)
    private EmbeddedGame game;

    @JsonProperty("category")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonDeserialize(using = EmbeddedCategoryDeserializer.class)
    private EmbeddedCategory category;

    @JsonProperty("level")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonDeserialize(using = EmbeddedLevelDeserializer.class)
    private EmbeddedLevel level;

    @JsonProperty("players")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonDeserialize(using = EmbeddedPlayersDeserializer.class)
    private EmbeddedPlayers players;

    @JsonProperty("region")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonDeserialize(using = EmbeddedRegionDeserializer.class)
    private Region region;

    @JsonProperty("platform")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonDeserialize(using = EmbeddedPlatformDeserializer.class)
    private Platform platform;

}
