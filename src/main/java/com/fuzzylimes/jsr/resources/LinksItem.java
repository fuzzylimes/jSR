package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Representation of a link resource
 */
@Data
public class LinksItem{

	/** what the resource is */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("rel")
	private String rel;

	/** link to the resource */
	@JsonProperty("uri")
	private String uri;
}