package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Developers are the persons and/of studios responsible for developing games, for example Acclaim Entertainment,
 * Bethesda Softworks, Edmund McMillen etc.
 */
@Data
public class Developer {

	@JsonProperty("name")
	private String name;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("id")
	private String id;
}