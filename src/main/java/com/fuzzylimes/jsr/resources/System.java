package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class System{

	@JsonProperty("emulated")
	private boolean emulated;

	@JsonProperty("region")
	private String region;

	@JsonProperty("platform")
	private String platform;
}