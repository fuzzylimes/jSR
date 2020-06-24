package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * How a game/series is represented across the world
 */
@Data
public class Names{

	/** Japanese name of game/series, if available */
	@JsonProperty("japanese")
	private String japanese;

	/** Name of game on twitch, if available. Not used with Series */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("twitch")
	private String twitch;

	/** International name of game/series, if available */
	@JsonProperty("international")
	private String international;
}