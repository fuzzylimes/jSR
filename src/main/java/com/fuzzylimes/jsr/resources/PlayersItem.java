package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * General details about a Player/User
 */
@Data
public class PlayersItem{

	/** whether or not the player is a user or guest */
	@JsonProperty("rel")
	private String rel;

	/** user name */
	@JsonProperty("name")
	private String name;

	/** link to user */
	@JsonProperty("uri")
	private String uri;

	/** internal user id */
	@JsonProperty("id")
	private String id;
}