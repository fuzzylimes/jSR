package com.fuzzylimes.jsr.resources;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fuzzylimes.jsr.util.*;
import lombok.Data;

@Data
public class Run{

	@JsonProperty("date")
	private String date;

	@JsonProperty("game")
	@JsonDeserialize(using = EmbeddedGameDeserializer.class, as = EmbeddedGame.class)
	private EmbeddedGame game;

	@JsonProperty("splits")
	private LinksItem splits;

	@JsonProperty("level")
	@JsonDeserialize(using = EmbeddedLevelDeserializer.class, as = EmbeddedLevel.class)
	private EmbeddedLevel level;

	@JsonProperty("players")
	@JsonDeserialize(using = EmbeddedPlayersDeserializer.class, as = EmbeddedPlayers.class)
	private EmbeddedPlayers players;

	/** This property is a mapping of a {@link Variable} id to one of its {@link VariableValues} */
	@JsonProperty("values")
	private Map<String,String> values;

	@JsonProperty("videos")
	private Videos videos;

	@JsonProperty("times")
	private Times times;

	@JsonProperty("submitted")
	private String submitted;

	@JsonProperty("system")
	private System system;

	@JsonProperty("weblink")
	private String weblink;

	@JsonProperty("comment")
	private String comment;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("id")
	private String id;

	@JsonProperty("category")
	@JsonDeserialize(using = EmbeddedCategoryDeserializer.class, as = EmbeddedCategory.class)
	private EmbeddedCategory category;

	@JsonProperty("status")
	private RunStatus status;

	@JsonProperty("region")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = EmbeddedRegionDeserializer.class)
	private Region region;

	@JsonProperty("platform")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = EmbeddedPlatformDeserializer.class)
	private Platform platform;
}