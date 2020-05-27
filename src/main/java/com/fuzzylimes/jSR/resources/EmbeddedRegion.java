package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmbeddedRegion {

	@JsonProperty("code")
	private String code;

	@JsonProperty("names")
	private Names names;
}