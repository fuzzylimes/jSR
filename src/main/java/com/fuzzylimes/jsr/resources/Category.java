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

	/** Whether or not this is a main category for the game */
	@JsonProperty("miscellaneous")
	private boolean miscellaneous;

	/** defines how many players may participate in the category */
	@JsonProperty("players")
	private CategoryPlayers players;

	/** name of the category */
	@JsonProperty("name")
	private String name;

	/** link to the category page */
	@JsonProperty("weblink")
	private String weblink;

	/** The rules for this category; text blob */
	@JsonProperty("rules")
	private String rules;

	/** resource links for the category */
	@JsonProperty("links")
	private List<LinksItem> links;

	/** internal id for the category */
	@JsonProperty("id")
	private String id;

	/** Supported category types */
	@JsonProperty("type")
	private CategoryTypes type;

	/** the related game object; only present when embedded query performed */
	@JsonProperty("game")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = GameDeserializer.class)
	private Game game;

	/** list of variables for the category; only present when embedded query performed */
	@JsonProperty("variables")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = VariableEmbedDeserializer.class)
	private List<Variable> variables;

}