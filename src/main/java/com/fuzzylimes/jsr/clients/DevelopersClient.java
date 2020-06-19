package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.query_parameters.sorting.DevelopersOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.Developer;
import com.fuzzylimes.jsr.resources.Guest;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;
import java.util.List;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

public class DevelopersClient {

    /**
     * Should not be initialized. Use the static references to each resource call
     */
    private DevelopersClient() {
        // Util method
    }

    /**
     * GET developers
     *
     * <p>Allows you to query for all developers in the database, sorted by the parameters defined in
     * {@link Sorting} of type {@link DevelopersOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link DevelopersOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/developers.md#get-developers">API Docs</a></li>
     * </ul>
     *
     * @param order Defined sorting order; {@link Sorting} of type {@link DevelopersOrderBy}
     * @return {@link PagedResponse} of {@link Developer} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Developer> getDevelopers(Sorting<DevelopersOrderBy> order) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(buildPath(BASE_RESOURCE, DEVELOPERS_PATH), order.getQueryMap());
        return mapper.readValue(node.toString(), new TypeReference<PagedResponse<Developer>>() {});
    }

    /**
     * GET developers
     *
     * <p>Allows you to query for all developers in the database.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/developers.md#get-developers">API Docs</a></li>
     * </ul>
     *
     * @return {@link PagedResponse} of {@link Developer} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Developer> getDevelopers() throws IOException, UnexpectedResponseException {
        return getDevelopers(Sorting.<DevelopersOrderBy>builder().build());
    }


    /**
     * GET developers/{id}
     *
     * <p>Allows you to query for a specific developer in the database.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/developers.md#get-developersid">API Docs</a></li>
     * </ul>
     *
     * @param id The Id of the {@link Developer} to be queried
     * @return {@link Developer} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Developer getDevelopersById(String id) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(buildPath(BASE_RESOURCE, DEVELOPERS_PATH, id));
        return mapper.readValue(node.get("data").toString(), Developer.class);
    }

}
