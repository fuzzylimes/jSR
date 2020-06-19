package com.fuzzylimes.jsr.resources;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fuzzylimes.jsr.util.*;
import lombok.Data;

@Data
public class Game{

	@JsonProperty("regions")
	@JsonDeserialize(using = EmbeddedRegionsDeserializer.class)
	private GameRegion regions;

	@JsonProperty("developers")
	@JsonDeserialize(using = EmbeddedDevelopersDeserializer.class)
	private EmbeddedDevelopers developers;

	@JsonProperty("release-date")
	private String releaseDate;

	@JsonProperty("created")
	private String created;

	@JsonProperty("ruleset")
	private Ruleset ruleset;

	@JsonProperty("abbreviation")
	private String abbreviation;

	@JsonProperty("platforms")
	private List<String> platforms;

	@JsonProperty("romhack")
	private boolean romhack;

	@JsonProperty("gametypes")
	@JsonDeserialize(using = EmbeddedGameTypesDeserializer.class)
	private EmbeddedGameTypes gametypes;

	@JsonProperty("names")
	private Names names;

	@JsonProperty("assets")
	private Assets assets;

	@JsonProperty("genres")
	@JsonDeserialize(using = EmbeddedGenresDeserializer.class)
	private EmbeddedGenres genres;

	@JsonProperty("engines")
	@JsonDeserialize(using = EmbeddedEnginesDeserializer.class)
	private EmbeddedEngines engines;

	@JsonProperty("weblink")
	private String weblink;

	@JsonProperty("publishers")
	@JsonDeserialize(using = EmbeddedPublishersDeserializer.class)
	private EmbeddedPublishers publishers;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("id")
	private String id;

	@JsonProperty("released")
	private int released;

	@JsonProperty("moderators")
	private Map<String, ModeratorRoles> moderators;
}