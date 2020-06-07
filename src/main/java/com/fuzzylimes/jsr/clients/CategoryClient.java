package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.query_parameters.CategoryRecordsQuery;
import com.fuzzylimes.jsr.resources.Category;
import com.fuzzylimes.jsr.resources.Leaderboard;
import com.fuzzylimes.jsr.resources.Variable;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.query_parameters.sorting.VariablesOrderBy;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;
import java.util.List;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

public class CategoryClient {

    /**
     * GET categories/{id} (embedded)
     *
     * <p>Used to retrieve a category resource by a specific category id with embedded values.
     *
     * <ul>
     *   <li>Supports embed with game,variables
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
                getSyncQuery(buildPath(BASE_RESOURCE, CATEGORIES_PATH, id), CATEGORY_EMBED):
                getSyncQuery(buildPath(BASE_RESOURCE, CATEGORIES_PATH, id));
        return mapper.readValue(node.get("data").toString(), Category.class);
    }

    /**
     * GET categories/{id}
     *
     * <p>Used to retrieve a category resource by a specific category id.
     *
     * <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/categories.md#get-categoriesid">
     *     API Docs</a>
     * </ul>
     *
     * @param id id of the category to query
     * @return {@link Category} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Category getCategoryById(String id) throws IOException, UnexpectedResponseException {
        return getCategoryById(id, null);
    }


    /**
     * GET categories/{id}/variables
     *
     * <p>Used to retrieve a list of {@link Variable} associated with a specific category id.
     *
     * <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/categories.md#get-categoriesid">
     *     API Docs</a>
     * </ul>
     *
     * @param id id of the category to query for variables
     * @return a List of {@link Variable} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Variable> getVariablesForCategory(String id) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(buildPath(BASE_RESOURCE, CATEGORIES_PATH, id));
        return mapper.readValue(node.get("data").toString(), new TypeReference<List<Variable>>() {});
    }


    /**
     * GET categories/{id}/variables
     *
     * <p>Used to retrieve a list of {@link Variable} associated with a specific category id, sorted by
     * the parameters defined in {@link Sorting} of type {@link VariablesOrderBy}.
     *
     * <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/categories.md#get-categoriesidvariables">
     *     API Docs</a>
     * </ul>
     *
     * @param id id of the category to query for variables
     * @param sorting defined sorting method {@link Sorting} of type {@link VariablesOrderBy}
     * @return a List of {@link Variable} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Variable> getVariablesForCategory(String id, Sorting<VariablesOrderBy> sorting) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(buildPath(BASE_RESOURCE, CATEGORIES_PATH, id, "variables"), sorting.getQueryMap());
        return mapper.readValue(node.get("data").toString(), new TypeReference<List<Variable>>() {});
    }


    /**
     * GET categories/{id}/records
     *
     * <p>Used to retrieve a list of {@link Leaderboard} records associated with a specific category id, filtered by
     * a set of provided {@link CategoryRecordsQuery} query params and optionally enriched with embedded data objects.
     * <ul>
     *   <li>Supports query parameters top and skip-empty
     *   <li>Supports embed with game,category,level,players,regions,platforms,variables</li>
     *   <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/categories.md#get-categoriesidrecords">
     *       API Docs</a>
     * </ul>
     *
     * @param id id of the category to query records for
     * @param embed whether or not to embed all additional, supported, embed items
     * @param queryParams {@link CategoryRecordsQuery} query parameters to be included in request
     * @return a List of {@link Leaderboard} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Leaderboard> getCategoryRecords(String id, Boolean embed, CategoryRecordsQuery queryParams) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(buildPath(BASE_RESOURCE, CATEGORIES_PATH, id), CATEGORY_EMBED, queryParams.getQueryMap()):
                getSyncQuery(buildPath(BASE_RESOURCE, CATEGORIES_PATH, id), queryParams.getQueryMap());
        return mapper.readValue(node.get("data").toString(), new TypeReference<List<Leaderboard>>() {});
    }

    /**
     * GET categories/{id}/records
     *
     * <p>Used to retrieve a list of {@link Leaderboard} records associated with a specific category id, optionally
     * enriched with embedded data objects.
     * <ul>
     *   <li>Supports embed with game,category,level,players,regions,platforms,variables</li>
     *   <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/categories.md#get-categoriesidrecords">
     *       API Docs</a>
     * </ul>
     *
     * @param id id of the category to query records for
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a List of {@link Leaderboard} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Leaderboard> getCategoryRecords(String id, Boolean embed) throws IOException, UnexpectedResponseException {
        return getCategoryRecords(id, embed, CategoryRecordsQuery.builder().build());
    }

    /**
     * GET categories/{id}/records
     *
     * <p>Used to retrieve a list of {@link Leaderboard} records associated with a specific category id
     * <ul>
     * <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/categories.md#get-categoriesidrecords">
     *     API Docs</a>
     * </ul>
     *
     * @param id id of the category to query records for
     * @return a List of {@link Leaderboard} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Leaderboard> getCategoryRecords(String id) throws IOException, UnexpectedResponseException {
        return getCategoryRecords(id, false);
    }
}
