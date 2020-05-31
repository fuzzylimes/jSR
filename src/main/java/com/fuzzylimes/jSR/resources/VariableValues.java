package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VariableValues {

	@JsonProperty("default")
	private String defaultValue;

	@JsonProperty("values")
	private Map<String, VariableValuesValue> values;
}