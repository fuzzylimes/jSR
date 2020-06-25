package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.JsrClient;
import com.fuzzylimes.jsr.common.Properties;
import com.fuzzylimes.jsr.query_parameters.CategoryRecordsQuery;
import com.fuzzylimes.jsr.resources.Category;
import com.fuzzylimes.jsr.resources.Leaderboard;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Variable;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.query_parameters.sorting.VariablesOrderBy;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;
import java.util.List;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

/**
 * <p>This client is used to make requests to the /categories set of resources on SpeedRun.com's API. The official documentation
 * for this set of APIs can be found in <a href="https://github.com/speedruncomorg/api/blob/master/version1/categories.md">the API Docs</a>.</p>
 *
 * <p>Categories are the different rulesets for speedruns. Categories are either per-game or per-level
 * (if the game uses individual levels), both can be accessed via this resource.</p>
 *
 * <p>The client uses static methods for all of the resource calls, so there is now need to initialize
 * anything to make a request. Simply reference the resource you wish to use to retrieve a related pojo.</p>
 */
public class CategoryClient {

    /**
     * Should not be initialized. Use the static references to each resource call
     */
    private CategoryClient() {
        // Util method
    }

    /**
     * GET categories/{id}
     *
     * <p>Used to retrieve a category resource by a specific category id, and optionally enriched with embedded data objects.
     *
     * <ul>
     *   <li>Supports embed with {@value Properties#CATEGORY_EMBED_VALUES}</li>
     *   <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/categories.md#get-categoriesid">API Docs</a></li>
     * </ul>
     *
     * @param id id of the category to query
     * @param embed whether or not to include embedded objects in response
     * @return {@link Category} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Category getCategoryById(String id, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, CATEGORIES_PATH, id), getCategoryEmbed()):
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, CATEGORIES_PATH, id));
        return mapper.readValue(node.get("data").toString(), Category.class);
    }

    /**
     * GET categories/{id}
     *
     * <p>Used to retrieve a category resource by a specific category id.</p>
     *
     * <ul>
     *   <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/categories.md#get-categoriesid">API Docs</a></li>
     * </ul>
     *
     * @param id id of the category to query
     * @return {@link Category} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Category getCategoryById(String id) throws IOException, UnexpectedResponseException {
        return getCategoryById(id, false);
    }


    /**
     * GET categories/{id}/variables
     *
     * <p>Used to retrieve a list of {@link Variable} associated with a specific category id.</p>
     *
     * <ul>
     *   <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/categories.md#get-categoriesid">API Docs</a></li>
     * </ul>
     *
     * @param id id of the category to query for variables
     * @return a List of {@link Variable} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Variable> getVariablesForCategory(String id) throws IOException, UnexpectedResponseException {
        return getVariablesForCategory(id, Sorting.<VariablesOrderBy>builder().build());
    }


    /**
     * GET categories/{id}/variables
     *
     * <p>Used to retrieve a list of {@link Variable} associated with a specific category id, sorted by
     * the parameters defined in {@link Sorting} of type {@link VariablesOrderBy}.</p>
     *
     * <ul>
     *   <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/categories.md#get-categoriesidvariables">API Docs</a></li>
     * </ul>
     *
     * @param id id of the category to query for variables
     * @param sorting defined sorting method {@link Sorting} of type {@link VariablesOrderBy}
     * @return a List of {@link Variable} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Variable> getVariablesForCategory(String id, Sorting<VariablesOrderBy> sorting) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, CATEGORIES_PATH, id, VARIABLES_PATH), sorting.getQueryMap());
        return mapper.readValue(node.get("data").toString(), new TypeReference<List<Variable>>() {});
    }


    /**
     * GET categories/{id}/records
     *
     * <p>Used to retrieve a list of {@link Leaderboard} records associated with a specific category id, filtered by
     * a set of provided {@link CategoryRecordsQuery} query params and optionally enriched with embedded data objects.</p>
     * <ul>
     *   <li>Supports query parameters defined in {@link CategoryRecordsQuery}</li>
     *   <li>Supports embedding with {@value Properties#LEADERBOARD_EMBED_VALUES}</li>
     *   <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/categories.md#get-categoriesidrecords">API Docs</a></li>
     * </ul>
     *
     * @param id id of the category to query records for
     * @param embed whether or not to embed all additional, supported, embed items
     * @param queryParams {@link CategoryRecordsQuery} query parameters to be included in request
     * @return a List of {@link Leaderboard} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Leaderboard> getCategoryRecords(String id, Boolean embed, CategoryRecordsQuery queryParams) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, CATEGORIES_PATH, id, RECORDS_PATH), getCategoryEmbed(), queryParams.getQueryMap()):
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, CATEGORIES_PATH, id, RECORDS_PATH), queryParams.getQueryMap());
        PagedResponse<Leaderboard> response = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Leaderboard>>() {});
        response.setType(Leaderboard.class);
        return response;
    }

    /**
     * GET categories/{id}/records
     *
     * <p>Used to retrieve a list of {@link Leaderboard} records associated with a specific category id, optionally
     * enriched with embedded data objects.</p>
     * <ul>
     *   <li>Supports embedding with {@value Properties#LEADERBOARD_EMBED_VALUES}</li>
     *   <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/categories.md#get-categoriesidrecords">API Docs</a></li>
     * </ul>
     *
     * @param id id of the category to query records for
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a List of {@link Leaderboard} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Leaderboard> getCategoryRecords(String id, Boolean embed) throws IOException, UnexpectedResponseException {
        return getCategoryRecords(id, embed, CategoryRecordsQuery.builder().build());
    }

    /**
     * GET categories/{id}/records
     *
     * <p>Used to retrieve a list of {@link Leaderboard} records associated with a specific category id</p>
     * <ul>
     *   <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/categories.md#get-categoriesidrecords">API Docs</a></li>
     * </ul>
     *
     * @param id id of the category to query records for
     * @return a List of {@link Leaderboard} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Leaderboard> getCategoryRecords(String id) throws IOException, UnexpectedResponseException {
        return getCategoryRecords(id, false);
    }
}
