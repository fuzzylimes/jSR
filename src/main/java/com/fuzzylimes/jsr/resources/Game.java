package com.fuzzylimes.jsr.resources;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fuzzylimes.jsr.util.EmbeddedRegionsDeserializer;
import lombok.Data;

@Data
public class Game{

	@JsonProperty("regions")
	@JsonDeserialize(using = EmbeddedRegionsDeserializer.class)
	private GameRegion regions;

	@JsonProperty("developers")
	private List<Object> developers;

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
	private List<Object> gametypes;

	@JsonProperty("names")
	private Names names;

	@JsonProperty("assets")
	private Assets assets;

	@JsonProperty("genres")
	private List<Object> genres;

	@JsonProperty("engines")
	private List<Object> engines;

	@JsonProperty("weblink")
	private String weblink;

	@JsonProperty("publishers")
	private List<Object> publishers;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("id")
	private String id;

	@JsonProperty("released")
	private int released;

	@JsonProperty("moderators")
	private Map<String, ModeratorRoles> moderators;
}