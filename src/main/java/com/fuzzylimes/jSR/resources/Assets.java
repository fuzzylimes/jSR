package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Assets{

	@JsonProperty("cover-small")
	private Asset coverSmall;

	@JsonProperty("trophy-1st")
	private Asset trophy1st;

	@JsonProperty("background")
	private Asset background;

	@JsonProperty("cover-medium")
	private Asset coverMedium;

	@JsonProperty("icon")
	private Asset icon;

	@JsonProperty("trophy-2nd")
	private Asset trophy2nd;

	@JsonProperty("trophy-4th")
	private Asset trophy4th;

	@JsonProperty("logo")
	private Asset logo;

	@JsonProperty("trophy-3rd")
	private Asset trophy3rd;

	@JsonProperty("foreground")
	private Asset foreground;

	@JsonProperty("cover-tiny")
	private Asset coverTiny;

	@JsonProperty("cover-large")
	private Asset coverLarge;
}