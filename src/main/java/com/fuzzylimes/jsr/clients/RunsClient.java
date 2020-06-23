package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.JsrClient;
import com.fuzzylimes.jsr.common.Properties;
import com.fuzzylimes.jsr.query_parameters.RunsQuery;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.RunsOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Run;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

/**
 * <p>This client is used to make requests to the /runs resource on SpeedRun.com's api. The official documentation
 * for this set of APIs can be found in <a href="https://github.com/speedruncomorg/api/blob/master/version1/runs.md">the API Docs</a>.</p>
 *
 * <p>Runs are the records that represent completed speed run attempts for a given game. A Run record is created
 * whenever a user submits a time to SpeedRun.com.</p>
 *
 * <p>For record keeping purposes, invalidated or obsoleted runs can still be retrieved via the APIs.</p>
 *
 * <p>The client uses static methods for all of the resource calls, so there is now need to initialize
 * anything to make a request. Simply reference the resource you wish to use to retrieve a related pojo.</p>
 */
public class RunsClient {

    /**
     * Should not be initialized. Use the static references to each resource call
     */
    private RunsClient() {
        // Util method
    }

    /**
     * GET runs
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Run} objects, filtered by a set of provided
     * {@link RunsQuery} query params, sorted by the parameters defined in {@link Sorting} of type
     * {@link RunsOrderBy}, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link RunsQuery}</li>
     *     <li>Supports Order by and Direction in {@link RunsOrderBy} and {@link Direction}
     *     <li>Supports embedding with {@value Properties#RUNS_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/runs.md#get-runs">API Docs</a></li>
     * </ul>
     *
     * @param queryParams Additional query params to filter the output, provided by {@link RunsQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link RunsOrderBy}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Run} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Run> getRuns(RunsQuery queryParams, Sorting<RunsOrderBy> order, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, RUNS_PATH), getRunsEmbed(), queryParams.getQueryMap(), order.getQueryMap()):
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, RUNS_PATH), queryParams.getQueryMap(), order.getQueryMap());
        return mapper.readValue(node.toString(), new TypeReference<PagedResponse<Run>>() {});
    }

    /**
     * GET runs
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Run} objects, filtered by a set of provided
     * {@link RunsQuery} query params, sorted by the parameters defined in {@link Sorting} of type
     * {@link RunsOrderBy}</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link RunsQuery}</li>
     *     <li>Supports Order by and Direction in {@link RunsOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/runs.md#get-runs">API Docs</a></li>
     * </ul>
     *
     * @param queryParams Additional query params to filter the output, provided by {@link RunsQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link RunsOrderBy}
     * @return a {@link PagedResponse} of {@link Run} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Run> getRuns(RunsQuery queryParams, Sorting<RunsOrderBy> order) throws IOException, UnexpectedResponseException {
        return getRuns(queryParams, order, false);
    }

    /**
     * GET runs
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Run} objects, sorted by the parameters defined
     * in {@link Sorting} of type {@link RunsOrderBy}, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link RunsOrderBy} and {@link Direction}
     *     <li>Supports embedding with {@value Properties#RUNS_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/runs.md#get-runs">API Docs</a></li>
     * </ul>
     *
     * @param order Defined sorting order; {@link Sorting} of type {@link RunsOrderBy}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Run} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Run> getRuns(Sorting<RunsOrderBy> order, Boolean embed) throws IOException, UnexpectedResponseException {
        return getRuns(RunsQuery.builder().build(), order, embed);
    }

    /**
     * GET runs
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Run} objects, filtered by a set of provided
     * {@link RunsQuery} query params, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link RunsQuery}</li>
     *     <li>Supports embedding with {@value Properties#RUNS_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/runs.md#get-runs">API Docs</a></li>
     * </ul>
     *
     * @param queryParams Additional query params to filter the output, provided by {@link RunsQuery}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Run} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Run> getRuns(RunsQuery queryParams, Boolean embed) throws IOException, UnexpectedResponseException {
        return getRuns(queryParams, Sorting.<RunsOrderBy>builder().build(), embed);
    }

    /**
     * GET runs
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Run} objects, filtered by a set of provided
     * {@link RunsQuery} query params</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link RunsQuery}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/runs.md#get-runs">API Docs</a></li>
     * </ul>
     *
     * @param queryParams Additional query params to filter the output, provided by {@link RunsQuery}
     * @return a {@link PagedResponse} of {@link Run} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Run> getRuns(RunsQuery queryParams) throws IOException, UnexpectedResponseException {
        return getRuns(queryParams, Sorting.<RunsOrderBy>builder().build(), false);
    }

    /**
     * GET runs
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Run} objects, sorted by the parameters defined in
     * {@link Sorting} of type {@link RunsOrderBy}</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link RunsOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/runs.md#get-runs">API Docs</a></li>
     * </ul>
     *
     * @param order Defined sorting order; {@link Sorting} of type {@link RunsOrderBy}
     * @return a {@link PagedResponse} of {@link Run} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Run> getRuns(Sorting<RunsOrderBy> order) throws IOException, UnexpectedResponseException {
        return getRuns(RunsQuery.builder().build(), order, false);
    }

    /**
     * GET runs
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Run} objects, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports embedding with {@value Properties#RUNS_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/runs.md#get-runs">API Docs</a></li>
     * </ul>
     *
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Run} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Run> getRuns(Boolean embed) throws IOException, UnexpectedResponseException {
        return getRuns(RunsQuery.builder().build(), Sorting.<RunsOrderBy>builder().build(), embed);
    }

    /**
     * GET runs
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Run} objects</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/runs.md#get-runs">API Docs</a></li>
     * </ul>
     *
     * @return a {@link PagedResponse} of {@link Run} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Run> getRuns() throws IOException, UnexpectedResponseException {
        return getRuns(RunsQuery.builder().build(), Sorting.<RunsOrderBy>builder().build(), false);
    }


    /**
     * GET runs/{id}
     *
     * <p>Used to retrieve a specific {@link Run} for the provided run Id, optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports embedding with {@value Properties#RUNS_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/runs.md#get-runsid">API Docs</a></li>
     * </ul>
     *
     * @param id The id of the run to be queried
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link Run} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Run getRunById(String id, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, RUNS_PATH, id), getRunsEmbed()):
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, RUNS_PATH, id));
        return mapper.readValue(node.get("data").toString(), Run.class);
    }

    /**
     * GET runs/{id}
     *
     * <p>Used to retrieve a specific {@link Run} for the provided run Id.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/runs.md#get-runsid">API Docs</a></li>
     * </ul>
     *
     * @param id The id of the run to be queried
     * @return a {@link Run} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Run getRunById(String id) throws IOException, UnexpectedResponseException {
        return getRunById(id, false);
    }
}
