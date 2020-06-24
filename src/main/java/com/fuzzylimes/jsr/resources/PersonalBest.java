package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fuzzylimes.jsr.util.*;
import lombok.Data;

/**
 * A user's personal best for a given run
 */
@Data
public class PersonalBest {

    /** details about the run */
    @JsonProperty("run")
    private Run run;

    /** position on leaderboard from run */
    @JsonProperty("place")
    private int place;

    /** related game object; only present if embedding enabled */
    @JsonProperty("game")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonDeserialize(using = EmbeddedGameDeserializer.class)
    private EmbeddedGame game;

    /** related category object; only present if embedding enabled */
    @JsonProperty("category")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonDeserialize(using = EmbeddedCategoryDeserializer.class)
    private EmbeddedCategory category;

    /** related level objects; only present if embedding enabled */
    @JsonProperty("level")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonDeserialize(using = EmbeddedLevelDeserializer.class)
    private EmbeddedLevel level;

    /** related player objects; only present if embedding enabled */
    @JsonProperty("players")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonDeserialize(using = EmbeddedPlayersDeserializer.class)
    private EmbeddedPlayers players;

    /** related region object; only present if embedding enabled */
    @JsonProperty("region")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonDeserialize(using = EmbeddedRegionDeserializer.class)
    private Region region;

    /** related platform object; only present if embedding enabled */
    @JsonProperty("platform")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonDeserialize(using = EmbeddedPlatformDeserializer.class)
    private Platform platform;

}
