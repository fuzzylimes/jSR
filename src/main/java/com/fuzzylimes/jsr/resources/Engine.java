package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Engines are software frameworks used in the creation of games, for example the DOOM engine, Unreal Engine, Geo-Mod etc.
 */
@Data
public class Engine {

	/** name of engine */
	@JsonProperty("name")
	private String name;

	/** resource links */
	@JsonProperty("links")
	private List<LinksItem> links;

	/** internal id */
	@JsonProperty("id")
	private String id;
}