package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Names{

	@JsonProperty("japanese")
	private String japanese;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("twitch")
	private String twitch;

	@JsonProperty("international")
	private String international;
}