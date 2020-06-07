package com.fuzzylimes.jsr.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Guest{

	@JsonProperty("name")
	private String name;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("rel")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String rel;
}