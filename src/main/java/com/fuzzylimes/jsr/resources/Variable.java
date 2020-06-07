package com.fuzzylimes.jsr.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Variable{

	@JsonProperty("is-subcategory")
	private boolean isSubcategory;

	@JsonProperty("scope")
	private Scope scope;

	@JsonProperty("values")
	private VariableValues values;

	@JsonProperty("name")
	private String name;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("id")
	private String id;

	@JsonProperty("user-defined")
	private boolean userDefined;

	@JsonProperty("category")
	private Object category;

	@JsonProperty("mandatory")
	private boolean mandatory;

	@JsonProperty("obsoletes")
	private boolean obsoletes;
}