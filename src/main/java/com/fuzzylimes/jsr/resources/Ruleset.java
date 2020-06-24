package com.fuzzylimes.jsr.resources;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Rules in place for running a game
 */
@Data
public class Ruleset{

	/** whether or not the game can be run on an emulator */
	@JsonProperty("emulators-allowed")
	private boolean emulatorsAllowed;

	/** the time tracking method to be used */
	@JsonProperty("default-time")
	private RunTimeTypes defaultTime;

	/** whether or not milliseconds must be shown on video */
	@JsonProperty("show-milliseconds")
	private boolean showMilliseconds;

	/** whether or not the run must be verified before being accepted */
	@JsonProperty("require-verification")
	private boolean requireVerification;

	/** whether or not video proof is required */
	@JsonProperty("require-video")
	private boolean requireVideo;

	/** list of times that can/should be given for any run of that game */
	@JsonProperty("run-times")
	private List<RunTimeTypes> runTimes;
}