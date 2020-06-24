package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Holds a run and its resulting place
 */
@Data
public class RunsItem{

	/** Run object */
	@JsonProperty("run")
	private Run run;

	/** the place that the run resulted in */
	@JsonProperty("place")
	private int place;
}