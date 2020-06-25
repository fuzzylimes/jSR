package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.JsrClient;
import com.fuzzylimes.jsr.query_parameters.sorting.GameTypesOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.GameType;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

/**
 * <p>This client is used to make requests to the /gametypes set of resources on SpeedRun.com's API. The official documentation
 * for this set of APIs can be found in <a href="https://github.com/speedruncomorg/api/blob/master/version1/gametypes.md">the API Docs</a>.</p>
 *
 * <p>Game types are classifications for unofficial games, for example ROM Hack, Fangame, Modification etc.</p>
 *
 * <p>The client uses static methods for all of the resource calls, so there is now need to initialize
 * anything to make a request. Simply reference the resource you wish to use to retrieve a related pojo.</p>
 */
public class GameTypesClient {

    /**
     * Should not be initialized. Use the static references to each resource call
     */
    private GameTypesClient() {
        // Util method
    }

    /**
     * GET gametypes
     *
     * <p>Allows you to query for all gametypes in the database, sorted by the parameters defined in
     * {@link Sorting} of type {@link GameTypesOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link GameTypesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/gametypes.md#get-gametypes">API Docs</a></li>
     * </ul>
     *
     * @param order Defined sorting order; {@link Sorting} of type {@link GameTypesOrderBy}
     * @return {@link PagedResponse} of {@link GameType} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<GameType> getGameTypes(Sorting<GameTypesOrderBy> order) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GAME_TYPES_PATH), order.getQueryMap());
        PagedResponse<GameType> response = mapper.readValue(node.toString(), new TypeReference<PagedResponse<GameType>>() {});
        response.setType(GameType.class);
        return response;
    }

    /**
     * GET gametypes
     *
     * <p>Allows you to query for all gametypes in the database.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/gametypes.md#get-gametypes">API Docs</a></li>
     * </ul>
     *
     * @return {@link PagedResponse} of {@link GameType} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<GameType> getGameTypes() throws IOException, UnexpectedResponseException {
        return getGameTypes(Sorting.<GameTypesOrderBy>builder().build());
    }


    /**
     * GET gametypes/{id}
     *
     * <p>Allows you to query for a specific gametype in the database.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/gametypes.md#get-gametypesid">API Docs</a></li>
     * </ul>
     *
     * @param id The Id of the {@link GameType} to be queried
     * @return {@link GameType} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static GameType getGameTypesById(String id) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GAME_TYPES_PATH, id));
        return mapper.readValue(node.get("data").toString(), GameType.class);
    }

}
