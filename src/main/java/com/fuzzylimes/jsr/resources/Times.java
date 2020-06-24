package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * The record times for a run
 */
@Data
public class Times{

	/** is the real-world time of the run in ISO 8601 */
	@JsonProperty("realtime")
	private String realtime;

	/** is the real-world time of the run in seconds.milliseconds */
	@JsonProperty("realtime_t")
	private float realtimeT;

	/** is the real-world time of the run, excluding the loading times in seconds.milliseconds */
	@JsonProperty("realtime_noloads_t")
	private float realtimeNoloadsT;

	/** is the time that is relevant for the leaderboard in seconds.milliseconds */
	@JsonProperty("primary_t")
	private float primaryT;

	/** is the time as measured by the game itself in seconds.milliseconds */
	@JsonProperty("ingame_t")
	private float ingameT;

	/** is the real-world time of the run, excluding the loading times in ISO 8601 */
	@JsonProperty("realtime_noloads")
	private String realtimeNoloads;

	/** is the time as measured by the game itself in ISO 8601 */
	@JsonProperty("ingame")
	private String ingame;

	/** is the time that is relevant for the leaderboard in ISO 8601*/
	@JsonProperty("primary")
	private String primary;
}