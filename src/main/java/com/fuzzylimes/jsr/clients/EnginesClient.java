package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.query_parameters.sorting.EnginesOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.Engine;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

public class EnginesClient {

    /**
     * Should not be initialized. Use the static references to each resource call
     */
    private EnginesClient() {
        // Util method
    }

    /**
     * GET engines
     *
     * <p>Allows you to query for all engines in the database, sorted by the parameters defined in
     * {@link Sorting} of type {@link EnginesOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link EnginesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/engines.md#get-engines">API Docs</a></li>
     * </ul>
     *
     * @param order Defined sorting order; {@link Sorting} of type {@link EnginesOrderBy}
     * @return {@link PagedResponse} of {@link Engine} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Engine> getEngines(Sorting<EnginesOrderBy> order) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(buildPath(BASE_RESOURCE, ENGINES_PATH), order.getQueryMap());
        return mapper.readValue(node.toString(), new TypeReference<PagedResponse<Engine>>() {});
    }

    /**
     * GET engines
     *
     * <p>Allows you to query for all engines in the database.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/engines.md#get-engines">API Docs</a></li>
     * </ul>
     *
     * @return {@link PagedResponse} of {@link Engine} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Engine> getEngines() throws IOException, UnexpectedResponseException {
        return getEngines(Sorting.<EnginesOrderBy>builder().build());
    }


    /**
     * GET engines/{id}
     *
     * <p>Allows you to query for a specific engine in the database.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/engines.md#get-enginesid">API Docs</a></li>
     * </ul>
     *
     * @param id The Id of the {@link Engine} to be queried
     * @return {@link Engine} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Engine getEnginesById(String id) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(buildPath(BASE_RESOURCE, ENGINES_PATH, id));
        return mapper.readValue(node.get("data").toString(), Engine.class);
    }

}
