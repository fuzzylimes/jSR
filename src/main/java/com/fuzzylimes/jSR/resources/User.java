package com.fuzzylimes.jSR.resources;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User{

	@JsonProperty("youtube")
	private LinksItem youtube;

	@JsonProperty("role")
	private UserRoles role;

	@JsonProperty("twitch")
	private LinksItem twitch;

	@JsonProperty("speedrunslive")
	private LinksItem speedrunslive;

	@JsonProperty("name-style")
	private NameStyle nameStyle;

	@JsonProperty("signup")
	private String signup;

	@JsonProperty("hitbox")
	private LinksItem hitbox;

	@JsonProperty("twitter")
	private LinksItem twitter;

	@JsonProperty("names")
	private UserNames names;

	@JsonProperty("weblink")
	private String weblink;

	@JsonProperty("location")
	private Location location;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("id")
	private String id;
}