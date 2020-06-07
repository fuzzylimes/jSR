package com.fuzzylimes.jsr.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fuzzylimes.jsr.util.EmbeddedCategoriesDeserializer;
import com.fuzzylimes.jsr.util.VariableEmbedDeserializer;
import lombok.Data;

@Data
public class Level{

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

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("categories")
	@JsonDeserialize(using = EmbeddedCategoriesDeserializer.class)
	private List<Category> categories;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("variables")
	@JsonDeserialize(using = VariableEmbedDeserializer.class)
	private List<Variable> variables;
}