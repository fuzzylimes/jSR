package com.fuzzylimes.jSR.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Category{

	@JsonProperty("miscellaneous")
	private boolean miscellaneous;

	@JsonProperty("players")
	private CategoryPlayers players;

	@JsonProperty("name")
	private String name;

	@JsonProperty("weblink")
	private String weblink;

	@JsonProperty("rules")
	private String rules;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("id")
	private String id;

	@JsonProperty("type")
	private CategoryTypes type;

	@JsonProperty("game")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Game game;

	@JsonProperty("variables")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Variable> variables;

}