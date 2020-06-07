package com.fuzzylimes.jsr.resources;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Videos{

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("text")
	private String text;
}