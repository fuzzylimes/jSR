package com.fuzzylimes.jsr.resources;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fuzzylimes.jsr.util.*;
import lombok.Data;

@Data
public class Leaderboard{

	@JsonProperty("video-only")
	private boolean videoOnly;

	@JsonProperty("game")
	@JsonDeserialize(using = EmbeddedGameDeserializer.class, as = EmbeddedGame.class)
	private EmbeddedGame game;

	@JsonProperty("level")
	@JsonDeserialize(using = EmbeddedLevelDeserializer.class, as = EmbeddedLevel.class)
	private EmbeddedLevel level;

	@JsonProperty("timing")
	private String timing;

	@JsonProperty("values")
	private Map<String, String> values;

	@JsonProperty("weblink")
	private String weblink;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("category")
	@JsonDeserialize(using = EmbeddedCategoryDeserializer.class, as = EmbeddedCategory.class)
	private EmbeddedCategory category;

	@JsonProperty("region")
	private String region;

	@JsonProperty("runs")
	private List<RunsItem> runs;

	@JsonProperty("platform")
	private String platform;

	@JsonProperty("emulators")
	private Boolean emulators;

	@JsonProperty("players")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = EmbeddedPlayersDeserializer.class)
	private EmbeddedPlayers players;

	@JsonProperty("regions")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = EmbeddedRegionsDeserializer.class)
	private EmbeddedRegions regions;

	@JsonProperty("platforms")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = EmbeddedPlatformDeserializer.class)
	private Platform platforms;

	@JsonProperty("variables")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = VariableEmbedDeserializer.class)
	private List<Variable> variables;
}