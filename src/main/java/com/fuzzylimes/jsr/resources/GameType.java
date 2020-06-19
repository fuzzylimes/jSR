package com.fuzzylimes.jsr.resources;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Game types are classifications for unofficial games, for example ROM Hack, Fangame, Modification etc.
 */
@Data
public class GameType{

	@JsonProperty("name")
	private String name;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("id")
	private String id;

	@JsonProperty("allows-base-game")
	private Boolean allowsBaseGame;
}