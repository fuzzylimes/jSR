package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Genres are classifications for games, for example Action, JRPG, Rogue-like etc.
 */
@Data
public class Genre {

	/** name of genre */
	@JsonProperty("name")
	private String name;

	/** resource links */
	@JsonProperty("links")
	private List<LinksItem> links;

	/** internal id */
	@JsonProperty("id")
	private String id;
}