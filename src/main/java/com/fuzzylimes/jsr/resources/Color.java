package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * How a name is displayed
 */
@Data
public class Color{

	/** how text is displayed in light mode */
	@JsonProperty("light")
	private String light;

	/** how text is displayed in dark mode */
	@JsonProperty("dark")
	private String dark;
}