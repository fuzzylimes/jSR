package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Defines the current status of a submitted run
 */
@Data
public class RunStatus {

	/** date verified by moderator */
	@JsonProperty("verify-date")
	private String verifyDate;

	/** moderator id that verified */
	@JsonProperty("examiner")
	private String examiner;

	/** current status of the run */
	@JsonProperty("status")
	private RunStatusTypes status;

	/** moderator notes */
	@JsonProperty("reason")
	private String reason;
}