package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NameStyle{

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("color")
	private Color color;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("color-from")
	private Color colorFrom;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("color-to")
	private Color colorTo;

	@JsonProperty("style")
	private String style;
}