package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.resources.Category;
import com.fuzzylimes.jsr.resources.Leaderboard;
import com.fuzzylimes.jsr.resources.Variable;
import com.fuzzylimes.jsr.sorting.Sorting;
import com.fuzzylimes.jsr.sorting.VariablesOrderBy;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;
import java.util.List;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

public class CategoryClient {

    /**
     * GET categories/{id}
     *
     * Returns a single category object
     * Supports embed with game,variables
     *
     * @param id id of the category to query
     * @param embed whether or not to include embedded objects in response
     * @return {@link Category} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public Category getCategoryById(String id, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(buildPath(BASE_RESOURCE, CATEGORIES_PATH, id), CATEGORY_EMBED):
                getSyncQuery(buildPath(BASE_RESOURCE, CATEGORIES_PATH, id));
        return mapper.readValue(node.get("data").toString(), Category.class);
    }

    /**
     * GET categories/{id}
     *
     * @param id id of the category to query
     * @return {@link Category} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public Category getCategoryById(String id) throws IOException, UnexpectedResponseException {
        return getCategoryById(id, null);
    }


    /**
     * GET categories/{id}/variables
     *
     * Returns a list of variable objects
     *
     * @param id id of the category to query for variables
     * @return a List of {@link Variable} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public List<Variable> getVariablesForCategory(String id) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(buildPath(BASE_RESOURCE, CATEGORIES_PATH, id));
        return mapper.readValue(node.get("data").toString(), new TypeReference<List<Variable>>() {});
    }


    /**
     * GET categories/{id}/variables
     *
     * Returns a list of variable objects
     *
     * @param id id of the category to query for variables
     * @return a List of {@link Variable} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public List<Variable> getVariablesForCategory(String id, Sorting<VariablesOrderBy> sorting) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(buildPath(BASE_RESOURCE, CATEGORIES_PATH, id));
        return mapper.readValue(node.get("data").toString(), new TypeReference<List<Variable>>() {});
    }


    /**
     * GET categories/{id}/records
     *
     * Returns a list of {@link Leaderboard} objects
     * Supports query parameters top and skip-empty
     * Supports embed with game,category,level,players,regions,platforms,variables
     * https://github.com/speedruncomorg/api/blob/master/version1/categories.md#get-categoriesidrecords
     *
     */
    public List<Leaderboard> getCategoryRecords(String id, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(buildPath(BASE_RESOURCE, CATEGORIES_PATH, id), CATEGORY_EMBED):
                getSyncQuery(buildPath(BASE_RESOURCE, CATEGORIES_PATH, id));
        return mapper.readValue(node.get("data").toString(), new TypeReference<List<Leaderboard>>() {});
    }
}
