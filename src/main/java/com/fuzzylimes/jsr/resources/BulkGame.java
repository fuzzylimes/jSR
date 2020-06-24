package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Representation of a Game returned in a Bulk request
 */
@Data
public class BulkGame{

	/** Name of game */
	@JsonProperty("names")
	private Names names;

	/** link to game page */
	@JsonProperty("weblink")
	private String weblink;

	/** internal id of game */
	@JsonProperty("id")
	private String id;

	/** shorthand game abbreviation that can be used in some queries */
	@JsonProperty("abbreviation")
	private String abbreviation;
}