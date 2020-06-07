package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RunsItem{

	@JsonProperty("run")
	private Run run;

	@JsonProperty("place")
	private int place;
}