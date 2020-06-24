package com.fuzzylimes.jsr.resources;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Platforms are hardware devices that run games, for example PC, NES, PS2 etc.
 */
@Data
public class Platform{

	/** name of platform */
	@JsonProperty("name")
	private String name;

	/** resource links */
	@JsonProperty("links")
	private List<LinksItem> links;

	/** internal id */
	@JsonProperty("id")
	private String id;

	/** year of release */
	@JsonProperty("released")
	private int released;
}