package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.common.Properties;
import com.fuzzylimes.jsr.query_parameters.SeriesQuery;
import com.fuzzylimes.jsr.query_parameters.UserQuery;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.SeriesOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.query_parameters.sorting.UsersOrderBy;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Series;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

public class SeriesClient {

    /**
     * Should not be initialized. Use the static references to each resource call
     */
    private SeriesClient() {
        // Util method
    }

    /**
     *  GET series
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Series} objects, filtered by a set of provided
     * {@link SeriesQuery} query params, sorted by the parameters defined in {@link Sorting} of type
     * {@link SeriesOrderBy}, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link SeriesQuery}</li>
     *     <li>Supports Order by and Direction in {@link SeriesOrderBy} and {@link Direction}
     *     <li>Supports embedding with {@value Properties#SERIES_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-series">API Docs</a></li>
     * </ul>
     *
     * @param queryParams Additional query params to filter the output, provided by {@link UserQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link UsersOrderBy}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public PagedResponse<Series> getSeries(SeriesQuery queryParams, Sorting<UsersOrderBy> order, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(buildPath(BASE_RESOURCE, SERIES_PATH), SERIES_EMBED, queryParams.getQueryMap(), order.getQueryMap()):
                getSyncQuery(buildPath(BASE_RESOURCE, SERIES_PATH), queryParams.getQueryMap(), order.getQueryMap());
        return mapper.readValue(node.toString(), new TypeReference<PagedResponse<Series>>() {});
    }

    /**
     *  GET series
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Series} objects, filtered by a set of provided
     * {@link SeriesQuery} query params, sorted by the parameters defined in {@link Sorting} of type
     * {@link SeriesOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link SeriesQuery}</li>
     *     <li>Supports Order by and Direction in {@link SeriesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-series">API Docs</a></li>
     * </ul>
     *
     * @param queryParams Additional query params to filter the output, provided by {@link UserQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link UsersOrderBy}
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public PagedResponse<Series> getSeries(SeriesQuery queryParams, Sorting<UsersOrderBy> order) throws IOException, UnexpectedResponseException {
        return getSeries(queryParams, order, false);
    }

    /**
     *  GET series
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Series} objects, filtered by a set of provided
     * {@link SeriesQuery} query params, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link SeriesQuery}</li>
     *     <li>Supports embedding with {@value Properties#SERIES_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-series">API Docs</a></li>
     * </ul>
     *
     * @param queryParams Additional query params to filter the output, provided by {@link UserQuery}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public PagedResponse<Series> getSeries(SeriesQuery queryParams, Boolean embed) throws IOException, UnexpectedResponseException {
        return getSeries(queryParams, Sorting.<UsersOrderBy>builder().build(), embed);
    }

    /**
     *  GET series
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Series} objects, sorted by the parameters defined in
     * {@link Sorting} of type {@link SeriesOrderBy}, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link SeriesOrderBy} and {@link Direction}
     *     <li>Supports embedding with {@value Properties#SERIES_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-series">API Docs</a></li>
     * </ul>
     *
     * @param order Defined sorting order; {@link Sorting} of type {@link UsersOrderBy}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public PagedResponse<Series> getSeries(Sorting<UsersOrderBy> order, Boolean embed) throws IOException, UnexpectedResponseException {
        return getSeries(SeriesQuery.builder().build(), order, embed);
    }

    /**
     *  GET series
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Series} objects, filtered by a set of provided
     * {@link SeriesQuery} query params.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link SeriesQuery}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-series">API Docs</a></li>
     * </ul>
     *
     * @param queryParams Additional query params to filter the output, provided by {@link UserQuery}
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public PagedResponse<Series> getSeries(SeriesQuery queryParams) throws IOException, UnexpectedResponseException {
        return getSeries(queryParams, Sorting.<UsersOrderBy>builder().build(), false);
    }

    /**
     *  GET series
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Series} objects, sorted by the parameters defined
     * in {@link Sorting} of type {@link SeriesOrderBy}</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link SeriesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-series">API Docs</a></li>
     * </ul>
     *
     * @param order Defined sorting order; {@link Sorting} of type {@link UsersOrderBy}
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public PagedResponse<Series> getSeries(Sorting<UsersOrderBy> order) throws IOException, UnexpectedResponseException {
        return getSeries(SeriesQuery.builder().build(), Sorting.<UsersOrderBy>builder().build(), false);
    }

    /**
     *  GET series
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Series} objects, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports embedding with {@value Properties#SERIES_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-series">API Docs</a></li>
     * </ul>
     *
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public PagedResponse<Series> getSeries(Boolean embed) throws IOException, UnexpectedResponseException {
        return getSeries(SeriesQuery.builder().build(), Sorting.<UsersOrderBy>builder().build(), embed);
    }

    /**
     *  GET series
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Series} objects.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-series">API Docs</a></li>
     * </ul>
     *
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public PagedResponse<Series> getSeries() throws IOException, UnexpectedResponseException {
        return getSeries(SeriesQuery.builder().build(), Sorting.<UsersOrderBy>builder().build(), false);
    }

    /**
     *  GET series/{id}
     *
     * <p>Used to retrieve specific {@link Series} object by its Id, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports embedding with {@value Properties#SERIES_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-seriesid">API Docs</a></li>
     * </ul>
     *
     * @param embed whether or not to embed all additional, supported, embed items
     * @param id the id of the Series to lookup
     * @return A {@link Series} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public Series getSeriesById(String id, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(buildPath(BASE_RESOURCE, SERIES_PATH, id), SERIES_EMBED):
                getSyncQuery(buildPath(BASE_RESOURCE, SERIES_PATH, id));
        return mapper.readValue(node.get("data").toString(), Series.class);
    }

    /**
     *  GET series/{id}
     *
     * <p>Used to retrieve specific {@link Series} object by its Id.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-seriesid">API Docs</a></li>
     * </ul>
     *
     * @param id the id of the Series to lookup
     * @return A {@link Series} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public Series getSeriesById(String id) throws IOException, UnexpectedResponseException {
        return getSeriesById(id, false);
    }

    // GET series/{id}/games
    // Returns a list of Game objects
    // Supports all filters as Games resource
    // Supports all sorting options as Games resource
    // Supports full embed list as Games resource
    // Supports bulk mode
    // https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-seriesidgames

}
