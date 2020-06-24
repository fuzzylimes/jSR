package com.fuzzylimes.jsr.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Sometimes, speedrun.com has runs done by players that have no account on the site yet. These runners are called
 * "guests" in the API. Except for a name, there is nothing we know about them.
 */
@Data
public class Guest{

	/** name of guest */
	@JsonProperty("name")
	private String name;

	/** resource links */
	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("rel")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String rel;
}