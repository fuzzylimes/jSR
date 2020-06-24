package com.fuzzylimes.jsr.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Users are the individuals who have registered an account on speedrun.com. Users submit (their) runs and moderate
 * games, besides other things that are not covered by this API (like writing posts in the forums).
 */
@Data
public class User{

	/** link to users' youtube */
	@JsonProperty("youtube")
	private LinksItem youtube;

	/** users role on the site */
	@JsonProperty("role")
	private UserRoles role;

	/** link to users' twitch*/
	@JsonProperty("twitch")
	private LinksItem twitch;

	/** link to users' speedrunslive */
	@JsonProperty("speedrunslive")
	private LinksItem speedrunslive;

	/** how the users' name is styled on the site */
	@JsonProperty("name-style")
	private NameStyle nameStyle;

	/** registration date */
	@JsonProperty("signup")
	private String signup;

	/** link to users' hitbox*/
	@JsonProperty("hitbox")
	private LinksItem hitbox;

	/** link to users' twitter*/
	@JsonProperty("twitter")
	private LinksItem twitter;

	/** username */
	@JsonProperty("names")
	private Names names;

	/** link to users' page*/
	@JsonProperty("weblink")
	private String weblink;

	/** where the user is located in the world */
	@JsonProperty("location")
	private Location location;

	/** user resource links */
	@JsonProperty("links")
	private List<LinksItem> links;

	/** internal id */
	@JsonProperty("id")
	private String id;

	@JsonProperty("rel")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String rel;
}