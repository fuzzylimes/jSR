package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Splits{

	@JsonProperty("rel")
	private String rel;

	@JsonProperty("uri")
	private String uri;
}