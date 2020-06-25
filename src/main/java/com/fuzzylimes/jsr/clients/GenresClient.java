package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.JsrClient;
import com.fuzzylimes.jsr.query_parameters.sorting.GenresOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.Genre;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

/**
 * <p>This client is used to make requests to the /genres set of resources on SpeedRun.com's API. The official documentation
 * for this set of APIs can be found in <a href="https://github.com/speedruncomorg/api/blob/master/version1/genres.md">the API Docs</a>.</p>
 *
 * <p>Genres are classifications for games, for example Action, JRPG, Rogue-like etc.</p>
 *
 * <p>The client uses static methods for all of the resource calls, so there is now need to initialize
 * anything to make a request. Simply reference the resource you wish to use to retrieve a related pojo.</p>
 */
public class GenresClient {

    /**
     * Should not be initialized. Use the static references to each resource call
     */
    private GenresClient() {
        // Util method
    }

    /**
     * GET genres
     *
     * <p>Allows you to query for all genres in the database, sorted by the parameters defined in
     * {@link Sorting} of type {@link GenresOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link GenresOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/genres.md#get-genres">API Docs</a></li>
     * </ul>
     *
     * @param order Defined sorting order; {@link Sorting} of type {@link GenresOrderBy}
     * @return {@link PagedResponse} of {@link Genre} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Genre> getGenres(Sorting<GenresOrderBy> order) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GENRES_PATH), order.getQueryMap());
        PagedResponse<Genre> response = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Genre>>() {});
        response.setType(Genre.class);
        return response;
    }

    /**
     * GET genres
     *
     * <p>Allows you to query for all genres in the database.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/genres.md#get-genres">API Docs</a></li>
     * </ul>
     *
     * @return {@link PagedResponse} of {@link Genre} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Genre> getGenres() throws IOException, UnexpectedResponseException {
        return getGenres(Sorting.<GenresOrderBy>builder().build());
    }


    /**
     * GET genres/{id}
     *
     * <p>Allows you to query for a specific genre in the database.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/genres.md#get-genresid">API Docs</a></li>
     * </ul>
     *
     * @param id The Id of the {@link Genre} to be queried
     * @return {@link Genre} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Genre getGenresById(String id) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GENRES_PATH, id));
        return mapper.readValue(node.get("data").toString(), Genre.class);
    }

}
