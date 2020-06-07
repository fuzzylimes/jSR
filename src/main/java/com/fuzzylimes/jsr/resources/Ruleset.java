package com.fuzzylimes.jsr.resources;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Ruleset{

	@JsonProperty("emulators-allowed")
	private boolean emulatorsAllowed;

	@JsonProperty("default-time")
	private String defaultTime;

	@JsonProperty("show-milliseconds")
	private boolean showMilliseconds;

	@JsonProperty("require-verification")
	private boolean requireVerification;

	@JsonProperty("require-video")
	private boolean requireVideo;

	@JsonProperty("run-times")
	private List<String> runTimes;
}