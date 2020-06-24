package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

/**
 * <p>values contains the possible values as well as the default one.</p>
 * <ul>
 *     <li> values contains the defined values for the variable. Each value has a label and, if the category is a
 *     subcategory, rules and a set of flags, of which miscellaneous is the only one defined right now. For
 *     non-subcategory values, rules and flags are not defined.</li>
 *     <li>default is the value ID to be used as a default. This field can be null.</li>
 * </ul>
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VariableValues {

	/** the default variable for the run */
	@JsonProperty("default")
	private String defaultValue;

	/** mapping of variable id to the rule sets for the variable */
	@JsonProperty("values")
	private Map<String, VariableValuesValue> values;
}