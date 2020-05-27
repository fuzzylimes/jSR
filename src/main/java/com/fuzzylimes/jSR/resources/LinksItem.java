package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LinksItem{

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("rel")
	private String rel;

	@JsonProperty("uri")
	private String uri;
}