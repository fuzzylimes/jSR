package com.fuzzylimes.jsr.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fuzzylimes.jsr.util.GameDeserializer;
import com.fuzzylimes.jsr.util.VariableEmbedDeserializer;
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
	@JsonDeserialize(using = GameDeserializer.class)
	private Game game;

	@JsonProperty("variables")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = VariableEmbedDeserializer.class)
	private List<Variable> variables;

}