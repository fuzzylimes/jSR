package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Regional information (i.e. state/city/etc)
 */
@Data
public class UserRegion{

	/** region representation; I see enums in their example, not sure what all this can be */
	@JsonProperty("code")
	private String code;

	/** name representation of region */
	@JsonProperty("names")
	private Names names;
}