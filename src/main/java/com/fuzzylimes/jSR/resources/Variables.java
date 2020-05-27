package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Variables {

	@JsonProperty("variableList")
	@JsonAlias("data")
	private List<Variable> variableList;

	@JsonProperty("pagination")
	private Pagination pagination;
}