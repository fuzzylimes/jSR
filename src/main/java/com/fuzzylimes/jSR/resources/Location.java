package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Location{

	@JsonProperty("country")
	private Country country;

	@JsonProperty("region")
	private UserRegion region;
}