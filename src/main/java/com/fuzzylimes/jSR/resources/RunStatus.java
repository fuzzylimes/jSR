package com.fuzzylimes.jSR.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RunStatus {

	@JsonProperty("verify-date")
	private String verifyDate;

	@JsonProperty("examiner")
	private String examiner;

	@JsonProperty("status")
	private RunStatusTypes status;

	@JsonProperty("reason")
	private String reason;
}