package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.JsrClient;
import com.fuzzylimes.jsr.common.Properties;
import com.fuzzylimes.jsr.query_parameters.LeaderboardQuery;
import com.fuzzylimes.jsr.resources.Category;
import com.fuzzylimes.jsr.resources.Game;
import com.fuzzylimes.jsr.resources.Leaderboard;
import com.fuzzylimes.jsr.resources.Level;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

/**
 * <p>This client is used to make requests to the /leaderboards set of resources on SpeedRun.com's API. The official documentation
 * for this set of APIs can be found in <a href="https://github.com/speedruncomorg/api/blob/master/version1/leaderboards.md">the API Docs</a>.</p>
 *
 * <p>Leaderboards contain non-obsolete runs, sorted by time descending. In contrast to raw runs, leaderboards are
 * automatically grouped according to the game/category/level rules that the moderators have defined.</p>
 *
 * <p>The client uses static methods for all of the resource calls, so there is now need to initialize
 * anything to make a request. Simply reference the resource you wish to use to retrieve a related pojo.</p>
 */
public class LeaderboardsClient {

    /**
     * Should not be initialized. Use the static references to each resource call
     */
    private LeaderboardsClient() {
        // Util method
    }

    /**
     *  GET /leaderboards/{game}/category/{category}
     *
     *  <p>Used to retrieve a leaderboard for a specific {@link Game} and {@link Category}, filtered by
     *  a set of provided {@link LeaderboardQuery} query params and optionally enriched with embedded data objects.
     *
     *  <p>The game and category can be either IDs (e.g. xldev513) or the respective abbreviations
     *  (e.g., /leaderboards/smw/category/Any1 will work as expected and redirect to the ID-based URLs).
     *
     *  <ul>
     *      <li>Supports query parameters defined in {@link LeaderboardQuery}</li>
     *      <li>Supports embedding with {@value Properties#LEADERBOARD_EMBED_VALUES}</li>
     *      <li>https://github.com/speedruncomorg/api/blob/master/version1/leaderboards.md#get-leaderboardsgamecategorycategory</li>
     *  </ul>
     *
     * @param game Either the ID of the game or the abbreviation of the game to be queried
     * @param category Either the ID of the category or the abbreviation of the category
     * @param embed whether or not to embed all additional, supported, embed items
     * @param queryParams {@link LeaderboardQuery} query params to be used to filter query
     * @return {@link Leaderboard} object for the specified criteria
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Leaderboard getLeaderboardForGameCategory(String game, String category, Boolean embed, LeaderboardQuery queryParams) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, LEADERBOARD_PATH, game, CATEGORY_PATH, category), getLeaderboardEmbed(), queryParams.getQueryMap()):
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, LEADERBOARD_PATH, game, CATEGORY_PATH, category), queryParams.getQueryMap());
        return mapper.readValue(node.get("data").toString(), Leaderboard.class);
    }


    /**
     *  GET /leaderboards/{game}/category/{category}
     *
     *  <p>Used to retrieve a leaderboard for a specific {@link Game} and {@link Category}, and optionally
     *  enriched with embedded data objects.
     *
     *  <p>The game and category can be either IDs (e.g. xldev513) or the respective abbreviations
     *  (e.g., /leaderboards/smw/category/Any1 will work as expected and redirect to the ID-based URLs).
     *
     *  <ul>
     *      <li>Supports embedding with {@value Properties#LEADERBOARD_EMBED_VALUES}</li>
     *      <li>https://github.com/speedruncomorg/api/blob/master/version1/leaderboards.md#get-leaderboardsgamecategorycategory</li>
     *  </ul>
     *
     * @param game Either the ID of the game or the abbreviation of the game to be queried
     * @param category Either the ID of the category or the abbreviation of the category
     * @param embed whether or not to embed all additional, supported, embed items
     * @return {@link Leaderboard} object for the specified criteria
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Leaderboard getLeaderboardForGameCategory(String game, String category, Boolean embed) throws IOException, UnexpectedResponseException {
        return getLeaderboardForGameCategory(game, category, embed, LeaderboardQuery.builder().build());
    }


    /**
     *  GET /leaderboards/{game}/category/{category}
     *
     *  <p>Used to retrieve a leaderboard for a specific {@link Game} and {@link Category}.
     *
     *  <p>The game and category can be either IDs (e.g. xldev513) or the respective abbreviations
     *  (e.g., /leaderboards/smw/category/Any1 will work as expected and redirect to the ID-based URLs).
     *
     *  <ul>
     *      <li>https://github.com/speedruncomorg/api/blob/master/version1/leaderboards.md#get-leaderboardsgamecategorycategory</li>
     *  </ul>
     *
     * @param game Either the ID of the game or the abbreviation of the game to be queried
     * @param category Either the ID of the category or the abbreviation of the category
     * @return {@link Leaderboard} object for the specified criteria
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Leaderboard getLeaderboardForGameCategory(String game, String category) throws IOException, UnexpectedResponseException {
        return getLeaderboardForGameCategory(game, category, false);
    }


    /**
     *  GET /leaderboards/{game}/level/{level}/{category}
     *
     *  <p>Used to retrieve a leaderboard for a specific {@link Category} of a {@link Game} {@link Level}, filtered by
     *  a set of provided {@link LeaderboardQuery} query params and optionally enriched with embedded data objects.
     *
     *  <p>The game, level and category can be either IDs (e.g. xldev513) or the respective abbreviations
     *  (e.g., /leaderboards/smw/level/Yoshis_Island_1/Any1 will work as expected and redirect to the ID-based URLs).
     *
     *  <ul>
     *      <li>Supports query parameters defined in {@link LeaderboardQuery}</li>
     *      <li>Supports embedding with {@value Properties#LEADERBOARD_EMBED_VALUES}</li>
     *      <li>https://github.com/speedruncomorg/api/blob/master/version1/leaderboards.md#get-leaderboardsgamelevellevelcategory</li>
     *  </ul>
     *
     * @param game Either the ID of the game or the abbreviation of the game to be queried
     * @param level Either the ID of the level or the abbreviation of the level
     * @param category Either the ID of the category or the abbreviation of the category
     * @param embed whether or not to embed all additional, supported, embed items
     * @param queryParams {@link LeaderboardQuery} query params to be used to filter query
     * @return {@link Leaderboard} object for the specified criteria
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Leaderboard getLeaderboardForGameLevelCategory(String game, String level, String category,
                                                                 Boolean embed, LeaderboardQuery queryParams)
            throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, LEADERBOARD_PATH, game, LEVEL_PATH, level, category), getLeaderboardEmbed(), queryParams.getQueryMap()):
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, LEADERBOARD_PATH, game, CATEGORY_PATH, category), queryParams.getQueryMap());
        return mapper.readValue(node.get("data").toString(), Leaderboard.class);
    }

    /**
     *  GET /leaderboards/{game}/level/{level}/{category}
     *
     *  <p>Used to retrieve a leaderboard for a specific {@link Category} of a {@link Game} {@link Level}
     *  and optionally enriched with embedded data objects.
     *
     *  <p>The game, level and category can be either IDs (e.g. xldev513) or the respective abbreviations
     *  (e.g., /leaderboards/smw/level/Yoshis_Island_1/Any1 will work as expected and redirect to the ID-based URLs).
     *
     *  <ul>
     *      <li>Supports embedding with {@value Properties#LEADERBOARD_EMBED_VALUES}</li>
     *      <li>https://github.com/speedruncomorg/api/blob/master/version1/leaderboards.md#get-leaderboardsgamelevellevelcategory</li>
     *  </ul>
     *
     * @param game Either the ID of the game or the abbreviation of the game to be queried
     * @param level Either the ID of the level or the abbreviation of the level
     * @param category Either the ID of the category or the abbreviation of the category
     * @param embed whether or not to embed all additional, supported, embed items
     * @return {@link Leaderboard} object for the specified criteria
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Leaderboard getLeaderboardForGameLevelCategory(String game, String level, String category,
                                                                 Boolean embed)
            throws IOException, UnexpectedResponseException {
        return getLeaderboardForGameLevelCategory(game, level, category, embed, LeaderboardQuery.builder().build());
    }


    /**
     *  GET /leaderboards/{game}/level/{level}/{category}
     *
     *  <p>Used to retrieve a leaderboard for a specific {@link Category} of a {@link Game} {@link Level}
     *  and optionally enriched with embedded data objects.
     *
     *  <p>The game, level and category can be either IDs (e.g. xldev513) or the respective abbreviations
     *  (e.g., /leaderboards/smw/level/Yoshis_Island_1/Any1 will work as expected and redirect to the ID-based URLs).
     *
     *  <ul>
     *      <li>https://github.com/speedruncomorg/api/blob/master/version1/leaderboards.md#get-leaderboardsgamelevellevelcategory</li>
     *  </ul>
     *
     * @param game Either the ID of the game or the abbreviation of the game to be queried
     * @param level Either the ID of the level or the abbreviation of the level
     * @param category Either the ID of the category or the abbreviation of the category
     * @return {@link Leaderboard} object for the specified criteria
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Leaderboard getLeaderboardForGameLevelCategory(String game, String level, String category)
            throws IOException, UnexpectedResponseException {
        return getLeaderboardForGameLevelCategory(game, level, category, false);
    }

}
