package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Regions {

	@JsonProperty("regionList")
	@JsonAlias("data")
	private List<Region> regionList;

	@JsonProperty("pagination")
	private Pagination pagination;
}