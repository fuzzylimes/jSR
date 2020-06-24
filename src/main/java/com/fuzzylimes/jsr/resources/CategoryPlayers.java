package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Defines the number of players allowed to participate in a category at the same time.
 */
@Data
public class CategoryPlayers {

	/** specifier for number assigned to value, either "exactly" or "up-to" */
	@JsonProperty("type")
	private CategoryPlayersTypes type;

	/** number of players allowed for the cateogry */
	@JsonProperty("value")
	private int value;
}