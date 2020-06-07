package com.fuzzylimes.jsr.resources;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Region{

	@JsonProperty("name")
	private String name;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("id")
	private String id;
}