package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Specifies how a variable relates to game. Variables may apply to an entire game, all-levels, a single-level, or all
 * of these options (defined in {@link ScopeTypes}).
 */
@Data
public class Scope{

	/** the scope to which the variable applies */
	@JsonProperty("type")
	private ScopeTypes type;

	/** if level representation, id of level */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("level")
	private String level;
}