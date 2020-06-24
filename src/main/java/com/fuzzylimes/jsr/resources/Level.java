package com.fuzzylimes.jsr.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fuzzylimes.jsr.util.EmbeddedCategoriesDeserializer;
import com.fuzzylimes.jsr.util.VariableEmbedDeserializer;
import lombok.Data;

/**
 * Levels are the stages/worlds/maps within a game. Not all games have levels.
 */
@Data
public class Level{

	/** name of level */
	@JsonProperty("name")
	private String name;

	/** link to page */
	@JsonProperty("weblink")
	private String weblink;

	/** rules for the level */
	@JsonProperty("rules")
	private String rules;

	/** resource links */
	@JsonProperty("links")
	private List<LinksItem> links;

	/** internal id */
	@JsonProperty("id")
	private String id;

	/** categories related to the level; only present when embedding enabled */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("categories")
	@JsonDeserialize(using = EmbeddedCategoriesDeserializer.class)
	private List<Category> categories;

	/** variables related tot he level; only present when embedding enabeled */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("variables")
	@JsonDeserialize(using = VariableEmbedDeserializer.class)
	private List<Variable> variables;
}