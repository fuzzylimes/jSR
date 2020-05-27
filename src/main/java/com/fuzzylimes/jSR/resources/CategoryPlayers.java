package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryPlayers {

	@JsonProperty("type")
	private String type;

	@JsonProperty("value")
	private int value;
}