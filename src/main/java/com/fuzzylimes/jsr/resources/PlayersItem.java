package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlayersItem{

	@JsonProperty("rel")
	private String rel;

	@JsonProperty("name")
	private String name;

	@JsonProperty("uri")
	private String uri;

	@JsonProperty("id")
	private String id;
}