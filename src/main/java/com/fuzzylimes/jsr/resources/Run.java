package com.fuzzylimes.jsr.resources;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fuzzylimes.jsr.util.*;
import lombok.Data;

/**
 * <p>Runs are the records that represent completed speed run attempts for a given game. A Run record is created
 * whenever a user submits a time to SpeedRun.com.</p>
 *
 * <p>For record keeping purposes, invalidated or obsoleted runs can still be retrieved via the APIs.</p>
 */
@Data
public class Run{

	/** date that the run was submitted */
	@JsonProperty("date")
	private String date;

	/**
	 * Game associated with a run. Will return a list of ids without embedding, and a list of game objects if
	 * embedding is enabled.
	 */
	@JsonProperty("game")
	@JsonDeserialize(using = EmbeddedGameDeserializer.class, as = EmbeddedGame.class)
	private EmbeddedGame game;

	/** link to the splits for the run */
	@JsonProperty("splits")
	private LinksItem splits;

	/**
	 * Level associated with a run. Will return a list of ids without embedding, and a list of game level if
	 * embedding is enabled.
	 */
	@JsonProperty("level")
	@JsonDeserialize(using = EmbeddedLevelDeserializer.class, as = EmbeddedLevel.class)
	private EmbeddedLevel level;

	/**
	 * Players associated with a run. Will return a list of ids without embedding, and a list of player objects if
	 * embedding is enabled.
	 */
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

	/**
	 * Category associated with a run. Will return a list of ids without embedding, and a list of category objects if
	 * embedding is enabled.
	 */
	@JsonProperty("category")
	@JsonDeserialize(using = EmbeddedCategoryDeserializer.class, as = EmbeddedCategory.class)
	private EmbeddedCategory category;

	@JsonProperty("status")
	private RunStatus status;

	/**
	 * Region associated with a run. Will return a list of ids without embedding, and a list of region objects if
	 * embedding is enabled.
	 */
	@JsonProperty("region")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = EmbeddedRegionDeserializer.class)
	private Region region;

	/**
	 * Platform associated with a run. Will return a list of ids without embedding, and a list of platform objects if
	 * embedding is enabled.
	 */
	@JsonProperty("platform")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = EmbeddedPlatformDeserializer.class)
	private Platform platform;
}