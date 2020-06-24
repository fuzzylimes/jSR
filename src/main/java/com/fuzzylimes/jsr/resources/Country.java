package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Representation of a country
 */
@Data
public class Country{

	/** country code (2-3 characters) */
	@JsonProperty("code")
	private String code;

	/** specific names for counteries */
	@JsonProperty("names")
	private Names names;
}