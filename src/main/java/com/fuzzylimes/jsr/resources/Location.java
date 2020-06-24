package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Representation of a place in the world
 */
@Data
public class Location{

	/** provides generic details about country */
	@JsonProperty("country")
	private Country country;

	/** provides more specific detail about the region the country is located in; may not always be present */
	@JsonProperty("region")
	private UserRegion region;
}