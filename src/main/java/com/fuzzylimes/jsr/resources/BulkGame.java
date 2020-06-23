package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BulkGame{

	@JsonProperty("names")
	private Names names;

	@JsonProperty("weblink")
	private String weblink;

	@JsonProperty("id")
	private String id;

	@JsonProperty("abbreviation")
	private String abbreviation;
}