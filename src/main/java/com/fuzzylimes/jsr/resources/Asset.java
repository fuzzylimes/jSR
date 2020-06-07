package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Asset {

	@JsonProperty("width")
	private int width;

	@JsonProperty("uri")
	private String uri;

	@JsonProperty("height")
	private int height;
}