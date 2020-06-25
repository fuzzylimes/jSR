package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.JsrClient;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.PlatformsOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Platform;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

/**
 * <p>This client is used to make requests to the /platforms set of resources on SpeedRun.com's API. The official documentation
 * for this set of APIs can be found in <a href="https://github.com/speedruncomorg/api/blob/master/version1/platforms.md">the API Docs</a>.</p>
 *
 * <p>Platforms are hardware devices that run games, for example PC, NES, PS2 etc.</p>
 *
 * <p>The client uses static methods for all of the resource calls, so there is now need to initialize
 * anything to make a request. Simply reference the resource you wish to use to retrieve a related pojo.</p>
 */
public class PlatformClient {

    private static final TypeReference<PagedResponse<Platform>> platformsType = new TypeReference<PagedResponse<Platform>>() {};

    /**
     * Should not be initialized. Use the static references to each resource call
     */
    private PlatformClient() {
        // Util method
    }

    /**
     * GET platforms
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Platform} objects, and sorted by the
     * parameters defined in {@link Sorting} of type {@link PlatformsOrderBy}</p>
     *
     * <ul>
     *     <li>Supports OrderBy and Direction using {@link PlatformsOrderBy} and {@link Direction}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/platforms.md#get-platforms">API Docs</a></li>
     * </ul>
     *
     * @param order Defined sorting order; {@link Sorting} of type {@link PlatformsOrderBy}
     * @return Platforms object with list of Platforms and pagination
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Platform> getPlatforms(Sorting<PlatformsOrderBy> order) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, PLATFORMS_PATH), order.getQueryMap());
        PagedResponse<Platform> response = mapper.readValue(node.toString(), platformsType);
        response.setType(Platform.class);
        return response;
    }

    /**
     * GET platforms
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Platform} objects</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/platforms.md#get-platforms">API Docs</a></li>
     * </ul>
     *
     * @return Platforms object with list of Platforms and pagination
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Platform> getPlatforms() throws IOException, UnexpectedResponseException {
        return getPlatforms(Sorting.<PlatformsOrderBy>builder().build());
    }


    /**
     * GET platforms/{id}
     *
     * <p>Used to retrieve a specific {@link Platform}</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/platforms.md#get-platformsid">API Docs</a></li>
     * </ul>
     *
     * @param id id of Platform to query
     * @return {@link Platform} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Platform getPlatformsById(String id) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, PLATFORMS_PATH, id));
        return mapper.readValue(node.get("data").toString(), Platform.class);
    }
}
