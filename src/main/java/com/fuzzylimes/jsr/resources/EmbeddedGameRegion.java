package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmbeddedGameRegion {

	@JsonProperty("code")
	private String code;

	@JsonProperty("names")
	private Names names;
}