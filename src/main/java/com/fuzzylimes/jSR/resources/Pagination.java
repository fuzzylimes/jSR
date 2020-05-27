package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Pagination {

	@JsonProperty("offset")
	private int offset;

	@JsonProperty("size")
	private int size;

	@JsonProperty("max")
	private int max;

	@JsonProperty("links")
	private List<LinksItem> links;
}