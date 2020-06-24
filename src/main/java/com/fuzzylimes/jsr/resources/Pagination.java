package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Specifies how to handle a paginated response. Provides details as to how much data matches a query, and how to
 * access the remainder of that data.
 */
@Data
public class Pagination {

	/** offset of paged request */
	@JsonProperty("offset")
	private int offset;

	/** number of records in response */
	@JsonProperty("size")
	private int size;

	/** max number of records that could be in response */
	@JsonProperty("max")
	private int max;

	/** navigation links if available */
	@JsonProperty("links")
	private List<LinksItem> links;
}