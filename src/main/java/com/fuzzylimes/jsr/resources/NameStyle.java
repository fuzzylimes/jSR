package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Defines how a name should be displayed.
 */
@Data
public class NameStyle{

	/** If type solid, represents the color */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("color")
	private Color color;

	/** If type gradient, represents the starting color of the gradient */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("color-from")
	private Color colorFrom;

	/** If type gradient, reprsents the ending color of the gradient */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("color-to")
	private Color colorTo;

	/**
	 * Names can be represented either by a single solid color, or by a gradient of two colors. If solid, then only
	 * the color attribute will be set. If gradient, colorTo and colorFrom will be set.
	 */
	@JsonProperty("style")
	private NameStyleTypes style;
}