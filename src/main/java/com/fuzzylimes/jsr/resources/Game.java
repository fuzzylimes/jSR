package com.fuzzylimes.jsr.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fuzzylimes.jsr.util.*;
import lombok.Data;

/**
 * Games are the thing that runs are being done against. They are exactly what they are called - the video game that
 * is being played.
 */
@Data
public class Game{

	/**
	 * Regions associated with a game. Will return a list of ids without embedding, and a list of region objects if
	 * embedding is enabled.
	 */
	@JsonProperty("regions")
	@JsonDeserialize(using = EmbeddedRegionsDeserializer.class)
	private EmbeddedRegions regions;

	/**
	 * Developers associated with a game. Will return a list of ids without embedding, and a list of developer objects if
	 * embedding is enabled.
	 */
	@JsonProperty("developers")
	@JsonDeserialize(using = EmbeddedDevelopersDeserializer.class)
	private EmbeddedDevelopers developers;

	/** date or release for the game */
	@JsonProperty("release-date")
	private String releaseDate;

	/** date that the game was added to the database */
	@JsonProperty("created")
	private String created;

	/** defines a set of agreed upon terms for running the game */
	@JsonProperty("ruleset")
	private Ruleset ruleset;

	/** short name for game; can be used in places instead of id */
	@JsonProperty("abbreviation")
	private String abbreviation;

	/**
	 * Platforms associated with a game. Will return a list of ids without embedding, and a list of platform objects if
	 * embedding is enabled.
	 */
	@JsonProperty("platforms")
	@JsonDeserialize(using = EmbeddedPlatformsDeserializer.class)
	private EmbeddedPlatforms platforms;

	/** whether or not the game is a romhack */
	@JsonProperty("romhack")
	private boolean romhack;

	/**
	 * GameTypes associated with a game. Will return a list of ids without embedding, and a list of gametype objects if
	 * embedding is enabled.
	 */
	@JsonProperty("gametypes")
	@JsonDeserialize(using = EmbeddedGameTypesDeserializer.class)
	private EmbeddedGameTypes gametypes;

	/** name that the game is known by */
	@JsonProperty("names")
	private Names names;

	/** media assets for the game */
	@JsonProperty("assets")
	private Assets assets;

	/**
	 * Genres associated with a game. Will return a list of ids without embedding, and a list of genre objects if
	 * embedding is enabled.
	 */
	@JsonProperty("genres")
	@JsonDeserialize(using = EmbeddedGenresDeserializer.class)
	private EmbeddedGenres genres;

	/**
	 * Engines associated with a game. Will return a list of ids without embedding, and a list of engine objects if
	 * embedding is enabled.
	 */
	@JsonProperty("engines")
	@JsonDeserialize(using = EmbeddedEnginesDeserializer.class)
	private EmbeddedEngines engines;

	/** link to page */
	@JsonProperty("weblink")
	private String weblink;

	/**
	 * Publishers associated with a game. Will return a list of ids without embedding, and a list of publisher objects if
	 * embedding is enabled.
	 */
	@JsonProperty("publishers")
	@JsonDeserialize(using = EmbeddedPublishersDeserializer.class)
	private EmbeddedPublishers publishers;

	/** resource links */
	@JsonProperty("links")
	private List<LinksItem> links;

	/** internal id */
	@JsonProperty("id")
	private String id;

	/** year released */
	@JsonProperty("released")
	private int released;

	/**
	 * Moderators associated with a game. Will return a list of ids without embedding, and a list of moderator objects if
	 * embedding is enabled.
	 */
	@JsonProperty("moderators")
	@JsonDeserialize(using = EmbeddedModeratorDeserializer.class)
	private Moderators moderators;

	/**
	 * List of Level objects associated with the game.
	 */
	@JsonProperty("levels")
	@JsonDeserialize(using = EmbeddedLevelsDeserializer.class)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Level> levels;

	/**
	 * List of Category objects associated with the game.
	 */
	@JsonProperty("categories")
	@JsonDeserialize(using = EmbeddedCategoriesDeserializer.class)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Category> categories;

	/**
	 * List of Variable objects associated with the game.
	 */
	@JsonProperty("variables")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = EmbeddedVariablesDeserializer.class)
	private List<Variable> variables;
}