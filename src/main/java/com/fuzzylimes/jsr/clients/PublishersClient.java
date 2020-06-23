package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.JsrClient;
import com.fuzzylimes.jsr.query_parameters.sorting.PublishersOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.Publisher;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

/**
 * <p>This client is used to make requests to the /publishers set of resources on SpeedRun.com's API. The official documentation
 * for this set of APIs can be found in <a href="https://github.com/speedruncomorg/api/blob/master/version1/publishers.md">the API Docs</a>.</p>
 *
 * <p>Publishers are companies that publish games, for example Capcom, SEGA, Midway Games etc.</p>
 *
 * <p>The client uses static methods for all of the resource calls, so there is now need to initialize
 * anything to make a request. Simply reference the resource you wish to use to retrieve a related pojo.</p>
 */
public class PublishersClient {

    /**
     * Should not be initialized. Use the static references to each resource call
     */
    private PublishersClient() {
        // Util method
    }

    /**
     * GET publishers
     *
     * <p>Allows you to query for all publishers in the database, sorted by the parameters defined in
     * {@link Sorting} of type {@link PublishersOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link PublishersOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/publishers.md#get-publishers">API Docs</a></li>
     * </ul>
     *
     * @param order Defined sorting order; {@link Sorting} of type {@link PublishersOrderBy}
     * @return {@link PagedResponse} of {@link Publisher} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Publisher> getPublishers(Sorting<PublishersOrderBy> order) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, PUBLISHERS_PATH), order.getQueryMap());
        return mapper.readValue(node.toString(), new TypeReference<PagedResponse<Publisher>>() {});
    }

    /**
     * GET publishers
     *
     * <p>Allows you to query for all publishers in the database.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/publishers.md#get-publishers">API Docs</a></li>
     * </ul>
     *
     * @return {@link PagedResponse} of {@link Publisher} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Publisher> getPublishers() throws IOException, UnexpectedResponseException {
        return getPublishers(Sorting.<PublishersOrderBy>builder().build());
    }


    /**
     * GET publishers/{id}
     *
     * <p>Allows you to query for a specific publisher in the database.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/publishers.md#get-publishersid">API Docs</a></li>
     * </ul>
     *
     * @param id The Id of the {@link Publisher} to be queried
     * @return {@link Publisher} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Publisher getPublishersById(String id) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, PUBLISHERS_PATH, id));
        return mapper.readValue(node.get("data").toString(), Publisher.class);
    }

}
