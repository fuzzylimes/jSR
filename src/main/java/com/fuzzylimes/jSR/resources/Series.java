package com.fuzzylimes.jSR.resources;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fuzzylimes.jSR.util.ModeratorDeserializer;
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
	@JsonDeserialize(using = ModeratorDeserializer.class, as = Moderators.class)
	private Moderators moderators;
}