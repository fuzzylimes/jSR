package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Color{

	@JsonProperty("light")
	private String light;

	@JsonProperty("dark")
	private String dark;
}