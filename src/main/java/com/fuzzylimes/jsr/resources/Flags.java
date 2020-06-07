package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Flags{

	@JsonProperty("miscellaneous")
	private boolean miscellaneous;
}