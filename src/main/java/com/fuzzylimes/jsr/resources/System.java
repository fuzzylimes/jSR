package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * The system/platform that a run was completed on
 */
@Data
public class System{

	/** whether or not the platform was emulated */
	@JsonProperty("emulated")
	private boolean emulated;

	/** the region of the system (i.e. NA vs PAL) */
	@JsonProperty("region")
	private String region;

	/** The platform completed on */
	@JsonProperty("platform")
	private String platform;
}