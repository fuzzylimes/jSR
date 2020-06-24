package com.fuzzylimes.jsr.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fuzzylimes.jsr.util.EmbeddedModeratorDeserializer;
import lombok.Data;

@Data
public class Series{

	@JsonProperty("names")
	private Names names;

	@JsonProperty("assets")
	private Assets assets;

	@JsonProperty("created")
	private String created;

	@JsonProperty("weblink")
	private String weblink;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("id")
	private String id;

	@JsonProperty("abbreviation")
	private String abbreviation;

	@JsonProperty("moderators")
	@JsonDeserialize(using = EmbeddedModeratorDeserializer.class)
	private Moderators moderators;
}