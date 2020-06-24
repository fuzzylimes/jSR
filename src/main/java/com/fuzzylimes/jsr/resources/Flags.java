package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Common settings for Variables
 */
@Data
public class Flags{

	/**	whether or not run is common */
	@JsonProperty("miscellaneous")
	private boolean miscellaneous;
}