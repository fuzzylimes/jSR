package com.fuzzylimes.jsr.resources;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * video evidence for a run
 */
@Data
public class Videos{

	/** set of links to run video(s) */
	@JsonProperty("links")
	private List<LinksItem> links;

	/** summary for run */
	@JsonProperty("text")
	private String text;
}