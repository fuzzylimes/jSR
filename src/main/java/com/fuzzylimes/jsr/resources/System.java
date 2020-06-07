package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class System{

	@JsonProperty("emulated")
	private boolean emulated;

	@JsonProperty("region")
	private Object region;

	@JsonProperty("platform")
	private String platform;
}