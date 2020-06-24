package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * An asset, most commonly an image, is a resource used to define non-text elements related to a record
 */
@Data
public class Asset {

	/** native width of the image */
	@JsonProperty("width")
	private int width;

	/** location of resource */
	@JsonProperty("uri")
	private String uri;

	/** native height of the image */
	@JsonProperty("height")
	private int height;
}