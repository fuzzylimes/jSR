package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserRegion{

	@JsonProperty("code")
	private String code;

	@JsonProperty("names")
	private UserNames names;
}