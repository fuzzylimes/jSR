package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.PlatformOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Platform;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

public class PlatformClient {

    private static final TypeReference<PagedResponse<Platform>> platformsType = new TypeReference<PagedResponse<Platform>>() {};

    /**
     * GET platforms
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Platform} objects, and sorted by the
     * parameters defined in {@link Sorting} of type {@link PlatformOrderBy}</p>
     *
     * <ul>
     *     <li>Supports OrderBy and Direction using {@link PlatformOrderBy} and {@link Direction}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/platforms.md#get-platforms">API Docs</a></li>
     * </ul>
     *
     * @return Platforms object with list of Platforms and pagination
     * @throws IOException if unable to retrieve response or parse response
     */
    public static PagedResponse<Platform> getPlatforms(Sorting<PlatformOrderBy> sorting) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(buildPath(BASE_RESOURCE, PLATFORMS_PATH), sorting.getQueryMap());
        return mapper.readValue(node.toString(), platformsType);
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
     * @throws IOException if unable to retrieve response or parse response
     */
    public static PagedResponse<Platform> getPlatforms() throws IOException, UnexpectedResponseException {
        return getPlatforms(Sorting.<PlatformOrderBy>builder().build());
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
     * @throws IOException if unable to retrieve response or parse response
     */
    public static Platform getPlatformsById(String id) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(buildPath(BASE_RESOURCE, PLATFORMS_PATH, id));
        return mapper.readValue(node.get("data").toString(), Platform.class);
    }
}
