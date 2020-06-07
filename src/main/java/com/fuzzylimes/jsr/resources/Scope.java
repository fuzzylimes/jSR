package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Scope{

	@JsonProperty("type")
	private ScopeTypes type;

	@JsonProperty("level")
	private String level;
}