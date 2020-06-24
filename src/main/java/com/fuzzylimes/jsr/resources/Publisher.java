package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Publishers are companies that publish games, for example Capcom, SEGA, Midway Games etc.
 */
@Data
public class Publisher {

	/** name of publisher */
	@JsonProperty("name")
	private String name;

	/** resource links */
	@JsonProperty("links")
	private List<LinksItem> links;

	/** internal id */
	@JsonProperty("id")
	private String id;
}