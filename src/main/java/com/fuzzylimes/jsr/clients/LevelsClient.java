package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.common.Properties;
import com.fuzzylimes.jsr.query_parameters.LevelCategoriesQuery;
import com.fuzzylimes.jsr.query_parameters.LevelLeaderboardsQuery;
import com.fuzzylimes.jsr.query_parameters.sorting.CategoriesOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.query_parameters.sorting.VariablesOrderBy;
import com.fuzzylimes.jsr.resources.*;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;
import java.util.List;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

public class LevelsClient {

    /**
     * Should not be initialized. Use the static references to each resource call
     */
    private LevelsClient() {
        // Util method
    }

    /**
     * GET levels/{id}
     *
     * <p>Used to retrieve a {@link Level} object for a specific Id, and optionally
     * enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports embedding with {@value Properties#LEVEL_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/levels.md#get-levelsid">API Docs</a></li>
     * </ul>
     *
     * @param levelId id of the {@link Level} to be queried
     * @param embed whether or not to embed all additional, supported, embed items
     * @return the found {@link Level} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Level getLevelById(String levelId, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(buildPath(BASE_RESOURCE, LEVELS_PATH, levelId), LEVEL_EMBED):
                getSyncQuery(buildPath(BASE_RESOURCE, LEVELS_PATH, levelId));
        return mapper.readValue(node.get("data").toString(), Level.class);
    }

    /**
     * GET levels/{id}
     *
     * <p>Used to retrieve a {@link Level} object for a specific Id</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/levels.md#get-levelsid">API Docs</a></li>
     * </ul>
     *
     * @param levelId id of the {@link Level} to be queried
     * @return the found {@link Level} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Level getLevelById(String levelId) throws IOException, UnexpectedResponseException {
        return getLevelById(levelId, false);
    }


    /**
     * GET levels/{id}/categories
     *
     * <p>Used to retrieve a list of {@link Category} objects associated with a given level id, filtered by
     * a set of provided {@link LevelCategoriesQuery} query params, and sorted by the parameters defined in {@link Sorting} of type
     * {@link VariablesOrderBy}</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link LevelCategoriesQuery}</li>
     *     <li>Supports Order by and Direction in {@link CategoriesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/levels.md#get-levelsidcategories">API Doc</a></li>
     * </ul>
     *
     * @param levelId The level id for which to query category information
     * @param queryParams Additional query params to filter the output, provided by {@link LevelCategoriesQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link CategoriesOrderBy}
     * @return a {@link List} of {@link Category}
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Category> getCategoriesForLevelId(String levelId, LevelCategoriesQuery queryParams, Sorting<CategoriesOrderBy> order) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(buildPath(BASE_RESOURCE, LEVELS_PATH, levelId, CATEGORIES_PATH), queryParams.getQueryMap(), order.getQueryMap());
        return mapper.readValue(node.get("data").toString(), new TypeReference<List<Category>>() {});
    }

    /**
     * GET levels/{id}/categories
     *
     * <p>Used to retrieve a list of {@link Category} objects associated with a given level id and filtered by
     * a set of provided {@link LevelCategoriesQuery} query params </p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link LevelCategoriesQuery}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/levels.md#get-levelsidcategories">API Doc</a></li>
     * </ul>
     *
     * @param levelId The level id for which to query category information
     * @param queryParams Additional query params to filter the output, provided by {@link LevelCategoriesQuery}
     * @return a {@link List} of {@link Category}
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Category> getCategoriesForLevelId(String levelId, LevelCategoriesQuery queryParams) throws IOException, UnexpectedResponseException {
        return getCategoriesForLevelId(levelId, queryParams, Sorting.<CategoriesOrderBy>builder().build());
    }

    /**
     * GET levels/{id}/categories
     *
     * <p>Used to retrieve a list of {@link Category} objects associated with a given level id, and sorted by the
     * parameters defined in {@link Sorting} of type {@link CategoriesOrderBy}</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link CategoriesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/levels.md#get-levelsidcategories">API Doc</a></li>
     * </ul>
     *
     * @param levelId The level id for which to query category information
     * @param order Defined sorting order; {@link Sorting} of type {@link CategoriesOrderBy}
     * @return a {@link List} of {@link Category}
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Category> getCategoriesForLevelId(String levelId, Sorting<CategoriesOrderBy> order) throws IOException, UnexpectedResponseException {
        return getCategoriesForLevelId(levelId, LevelCategoriesQuery.builder().build(), order);
    }

    /**
     * GET levels/{id}/categories
     *
     * <p>Used to retrieve a list of {@link Category} objects associated with a given level id</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/levels.md#get-levelsidcategories">API Doc</a></li>
     * </ul>
     *
     * @param levelId The level id for which to query category information
     * @return a {@link List} of {@link Category}
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Category> getCategoriesForLevelId(String levelId) throws IOException, UnexpectedResponseException {
        return getCategoriesForLevelId(levelId, LevelCategoriesQuery.builder().build(), Sorting.<CategoriesOrderBy>builder().build());
    }


    /**
     * GET levels/{id}/variables
     *
     * <p>Used to retrieve a list of {@link Variable} objects associated with a given level id, sorted by the
     * parameters defined in {@link Sorting} of type {@link VariablesOrderBy}</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link VariablesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/levels.md#get-levelsidvariables">API Doc</a></li>
     * </ul>
     *
     * @param levelId The level id for which to query category information
     * @param order Defined sorting order; {@link Sorting} of type {@link CategoriesOrderBy}
     * @return a {@link List} of {@link Category}
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Variable> getVaribaleForLevelId(String levelId, Sorting<VariablesOrderBy> order) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(buildPath(BASE_RESOURCE, LEVELS_PATH, levelId, VARIABLES_PATH), order.getQueryMap());
        return mapper.readValue(node.get("data").toString(), new TypeReference<List<Variable>>() {});
    }

    /**
     * GET levels/{id}/variables
     *
     * <p>Used to retrieve a list of {@link Variable} objects associated with a given level id</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/levels.md#get-levelsidvariables">API Doc</a></li>
     * </ul>
     *
     * @param levelId The level id for which to query category information
     * @return a {@link List} of {@link Category}
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Variable> getVaribaleForLevelId(String levelId) throws IOException, UnexpectedResponseException {
        return getVaribaleForLevelId(levelId, Sorting.<VariablesOrderBy>builder().build());
    }


    /**
     * GET levels/{id}/records
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Leaderboard} objects associated with a given level id,
     * filtered by a set of provided {@link LevelLeaderboardsQuery} query params, and optionally enriched with
     * embedded data objects
     *
     * <ul>
     *     <li>Supports query parameters available in {@link LevelLeaderboardsQuery}</li>
     *     <li>Supports embedding with {@value Properties#LEADERBOARD_EMBED_VALUES}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/levels.md#get-levelsidrecords">API Doc</a></li>
     * </ul>
     *
     * @param levelId The level id for which to query category information
     * @param embed whether or not to embed all additional, supported, embed items
     * @param queryParams Additional query params to filter the output, provided by {@link LevelLeaderboardsQuery}
     * @return a {@link PagedResponse} of {@link Leaderboard}
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Leaderboard> getLeaderboardForLevelId(String levelId, boolean embed, LevelLeaderboardsQuery queryParams) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(buildPath(BASE_RESOURCE, LEVELS_PATH, levelId, RECORDS_PATH), LEADERBOARD_EMBED, queryParams.getQueryMap()):
                getSyncQuery(buildPath(BASE_RESOURCE, LEVELS_PATH, levelId, RECORDS_PATH), queryParams.getQueryMap());
        return mapper.readValue(node.toString(), new TypeReference<PagedResponse<Leaderboard>>() {});
    }

    /**
     * GET levels/{id}/records
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Leaderboard} objects associated with a given level id,
     * filtered by a set of provided {@link LevelLeaderboardsQuery} query params
     *
     * <ul>
     *     <li>Supports query parameters available in {@link LevelLeaderboardsQuery}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/levels.md#get-levelsidrecords">API Doc</a></li>
     * </ul>
     *
     * @param levelId The level id for which to query category information
     * @param queryParams Additional query params to filter the output, provided by {@link LevelLeaderboardsQuery}
     * @return a {@link PagedResponse} of {@link Leaderboard}
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Leaderboard> getLeaderboardForLevelId(String levelId, LevelLeaderboardsQuery queryParams) throws IOException, UnexpectedResponseException {
        return getLeaderboardForLevelId(levelId, false, queryParams);
    }

    /**
     * GET levels/{id}/records
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Leaderboard} objects associated with a given level id,
     * and optionally enriched with embedded data objects
     *
     * <ul>
     *     <li>Supports embedding with {@value Properties#LEADERBOARD_EMBED_VALUES}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/levels.md#get-levelsidrecords">API Doc</a></li>
     * </ul>
     *
     * @param levelId The level id for which to query category information
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Leaderboard}
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Leaderboard> getLeaderboardForLevelId(String levelId, Boolean embed) throws IOException, UnexpectedResponseException {
        return getLeaderboardForLevelId(levelId, embed, LevelLeaderboardsQuery.builder().build());
    }


    /**
     * GET levels/{id}/records
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Leaderboard} objects associated with a given level id</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/levels.md#get-levelsidrecords">API Doc</a></li>
     * </ul>
     *
     * @param levelId The level id for which to query category information
     * @return a {@link PagedResponse} of {@link Leaderboard}
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Leaderboard> getLeaderboardForLevelId(String levelId) throws IOException, UnexpectedResponseException {
        return getLeaderboardForLevelId(levelId, false, LevelLeaderboardsQuery.builder().build());
    }
}
