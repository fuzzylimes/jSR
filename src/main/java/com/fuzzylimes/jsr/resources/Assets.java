package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Collection of {@link Asset} objects for a given record. These contain all of the related media assets needed to
 * construct a page for the record/game
 */
@Data
public class Assets{

	/** thumbnail image of cover for game/series */
	@JsonProperty("cover-tiny")
	private Asset coverTiny;

	/** small image of cover for game/series */
	@JsonProperty("cover-small")
	private Asset coverSmall;

	/** medium image of cover for game/series */
	@JsonProperty("cover-medium")
	private Asset coverMedium;

	/** large image of cover for game/series */
	@JsonProperty("cover-large")
	private Asset coverLarge;

	/** 1st place trophy resource */
	@JsonProperty("trophy-1st")
	private Asset trophy1st;

	/** 2nd place trophy resource */
	@JsonProperty("trophy-2nd")
	private Asset trophy2nd;

	/** 3rd place trophy resource */
	@JsonProperty("trophy-3rd")
	private Asset trophy3rd;

	/** 4th place trophy resource */
	@JsonProperty("trophy-4th")
	private Asset trophy4th;

	/** icon representation for resource, most likely cover */
	@JsonProperty("icon")
	private Asset icon;

	/** logo for game/series */
	@JsonProperty("logo")
	private Asset logo;

	/** banner image */
	@JsonProperty("foreground")
	private Asset foreground;

	/** background image */
	@JsonProperty("background")
	private Asset background;
}