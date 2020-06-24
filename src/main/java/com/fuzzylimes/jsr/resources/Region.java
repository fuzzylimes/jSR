package com.fuzzylimes.jsr.resources;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Regions represent the different distribution zones in which games are published, for example the US, Europe or Japan.
 */
@Data
public class Region{

	/** name of region */
	@JsonProperty("name")
	private String name;

	/** resource links */
	@JsonProperty("links")
	private List<LinksItem> links;

	/** internal id */
	@JsonProperty("id")
	private String id;
}