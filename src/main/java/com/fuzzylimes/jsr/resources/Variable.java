package com.fuzzylimes.jsr.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fuzzylimes.jsr.clients.CategoryClient;
import com.fuzzylimes.jsr.clients.GameClient;
import com.fuzzylimes.jsr.clients.LevelsClient;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import lombok.Data;

/**
 * <p>Variables are custom criteria to distinguish between runs done in the same category or level. The speed in
 * Mario Kart games (which can be 50cc, 100cc or 150cc) is an example for a variable that has 3 possible values.</p>
 *
 * <p>Variables are defined per-game and can be applicable to either all runs for this game or just full-game or
 * individual-level (IL) runs. Variables can also be restricted to a category. It is therefore important to understand
 * how to get the correct set of variables:</p>
 * <ul>
 *     <li>Use {@link GameClient}s {@link GameClient#getVariablesForGame(String, Sorting)} to get all defined variables
 *     of that game, no matter how they are configured.</li>
 *     <li>Use {@link GameClient}s {@link CategoryClient#getVariablesForCategory(String, Sorting)} to only get the
 *     variables that apply to the given category.</li>
 *     <li>Use {@link GameClient}s {@link LevelsClient#getVaribaleForLevelId(String, Sorting)} to only get the
 *     variables that apply to the given level.</li>
 * </ul>
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Variable{

	/**
	 * primarily a property that controls the user interface for selecting leaderboards on speedrun.com. Even if a
	 * variable is displayed as a sub category, you still need a regular category to define a leaderboard. But
	 * you can take this as a hint on how this values should be handled.
	 */
	@JsonProperty("is-subcategory")
	private boolean isSubcategory;

	/** scopes the variables to runs for the game */
	@JsonProperty("scope")
	private Scope scope;

	/** specifics for variable */
	@JsonProperty("values")
	private VariableValues values;

	/** name for the variable */
	@JsonProperty("name")
	private String name;

	/** resource links */
	@JsonProperty("links")
	private List<LinksItem> links;

	/** internal id */
	@JsonProperty("id")
	private String id;

	/**
	 * if true, the user can give a custom value when submitting the run. This custom value is stored just like
	 * predefined ones, so there is no different handling needed for these.
	 */
	@JsonProperty("user-defined")
	private boolean userDefined;

	/** category can be either null if the variable applies to all categories or the ID of the one category the variable applies to. */
	@JsonProperty("category")
	private Object category;

	/** When mandatory is true, newly submitted runs must include a value for this variable. */
	@JsonProperty("mandatory")
	private boolean mandatory;

	/** When obsoletes is true, the variable is taken into consideration when collecting runs for the leaderboard. */
	@JsonProperty("obsoletes")
	private boolean obsoletes;
}