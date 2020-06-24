package com.fuzzylimes.jsr.resources;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Game types are classifications for unofficial games, for example ROM Hack, Fangame, Modification etc.
 */
@Data
public class GameType{

	/** name of game type */
	@JsonProperty("name")
	private String name;

	/** resource links */
	@JsonProperty("links")
	private List<LinksItem> links;

	/** internal id */
	@JsonProperty("id")
	private String id;

	/** whether or not it can be run on the base game */
	@JsonProperty("allows-base-game")
	private Boolean allowsBaseGame;
}