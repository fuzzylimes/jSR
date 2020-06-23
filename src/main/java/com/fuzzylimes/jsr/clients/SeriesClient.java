package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.JsrClient;
import com.fuzzylimes.jsr.common.Properties;
import com.fuzzylimes.jsr.query_parameters.GamesQuery;
import com.fuzzylimes.jsr.query_parameters.SeriesQuery;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.GamesOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.SeriesOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.BulkGame;
import com.fuzzylimes.jsr.resources.Game;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Series;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

/**
 * <p>This client is used to make requests to the /series set of resources on SpeedRun.com's API. The official documentation
 * for this set of APIs can be found in <a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md">the API Docs</a>.</p>
 *
 * <p>Series are collections of games that have been released in context to each other, for example the "GTA" series
 * contains all the games of this franchise. As a series is most often formed after a number of games have been
 * published, many games do not belong to a specific series and are therefore categorized in the "Other" series. As
 * time progresses, games can be moved in their own series, so be prepared for the series-game relationship to change.</p>
 *
 * <p>The client uses static methods for all of the resource calls, so there is now need to initialize
 * anything to make a request. Simply reference the resource you wish to use to retrieve a related pojo.</p>
 */
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
     * @param queryParams Additional query params to filter the output, provided by {@link SeriesQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link SeriesOrderBy}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Series> getSeries(SeriesQuery queryParams, Sorting<SeriesOrderBy> order, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, SERIES_PATH), getSeriesEmbed(), queryParams.getQueryMap(), order.getQueryMap()):
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, SERIES_PATH), queryParams.getQueryMap(), order.getQueryMap());
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
     * @param queryParams Additional query params to filter the output, provided by {@link SeriesQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link SeriesOrderBy}
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Series> getSeries(SeriesQuery queryParams, Sorting<SeriesOrderBy> order) throws IOException, UnexpectedResponseException {
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
     * @param queryParams Additional query params to filter the output, provided by {@link SeriesQuery}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Series> getSeries(SeriesQuery queryParams, Boolean embed) throws IOException, UnexpectedResponseException {
        return getSeries(queryParams, Sorting.<SeriesOrderBy>builder().build(), embed);
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
     * @param order Defined sorting order; {@link Sorting} of type {@link SeriesOrderBy}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Series> getSeries(Sorting<SeriesOrderBy> order, Boolean embed) throws IOException, UnexpectedResponseException {
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
     * @param queryParams Additional query params to filter the output, provided by {@link SeriesQuery}
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Series> getSeries(SeriesQuery queryParams) throws IOException, UnexpectedResponseException {
        return getSeries(queryParams, Sorting.<SeriesOrderBy>builder().build(), false);
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
     * @param order Defined sorting order; {@link Sorting} of type {@link SeriesOrderBy}
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Series> getSeries(Sorting<SeriesOrderBy> order) throws IOException, UnexpectedResponseException {
        return getSeries(SeriesQuery.builder().build(), order, false);
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
    public static PagedResponse<Series> getSeries(Boolean embed) throws IOException, UnexpectedResponseException {
        return getSeries(SeriesQuery.builder().build(), Sorting.<SeriesOrderBy>builder().build(), embed);
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
    public static PagedResponse<Series> getSeries() throws IOException, UnexpectedResponseException {
        return getSeries(SeriesQuery.builder().build(), Sorting.<SeriesOrderBy>builder().build(), false);
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
    public static Series getSeriesById(String id, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, SERIES_PATH, id), getSeriesEmbed()):
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, SERIES_PATH, id));
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
    public static Series getSeriesById(String id) throws IOException, UnexpectedResponseException {
        return getSeriesById(id, false);
    }

    /**
     *  GET series/{id}/games
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Game}s associated with a series, filtered by a set of provided
     * {@link GamesQuery} query params, sorted by the parameters defined in {@link Sorting} of type
     * {@link GamesOrderBy}, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link GamesQuery}</li>
     *     <li>Supports Order by and Direction in {@link GamesOrderBy} and {@link Direction}
     *     <li>Supports embedding with {@value Properties#GAME_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-seriesidgames">API Docs</a></li>
     * </ul>
     *
     * @param id the id of the Series to lookup
     * @param queryParams Additional query params to filter the output, provided by {@link GamesQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link GamesOrderBy}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getGamesForSeries(String id, GamesQuery queryParams, Sorting<GamesOrderBy> order, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, SERIES_PATH, id, GAME_PATH), getGameEmbed(), queryParams.getQueryMap(), order.getQueryMap()):
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, SERIES_PATH, id, GAME_PATH), queryParams.getQueryMap(), order.getQueryMap());
        return mapper.readValue(node.toString(), new TypeReference<PagedResponse<Game>>() {});
    }

    /**
     *  GET series/{id}/games
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Game}s associated with a series, filtered by a set of provided
     * {@link GamesQuery} query params, sorted by the parameters defined in {@link Sorting} of type
     * {@link GamesOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link GamesQuery}</li>
     *     <li>Supports Order by and Direction in {@link GamesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-seriesidgames">API Docs</a></li>
     * </ul>
     *
     * @param id the id of the Series to lookup
     * @param queryParams Additional query params to filter the output, provided by {@link GamesQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link GamesOrderBy}
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getGamesForSeries(String id, GamesQuery queryParams, Sorting<GamesOrderBy> order) throws IOException, UnexpectedResponseException {
        return getGamesForSeries(id, queryParams, order, false);
    }

    /**
     *  GET series/{id}/games
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Game}s associated with a series, filtered by a set of provided
     * {@link GamesQuery} query params, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link GamesQuery}</li>
     *     <li>Supports embedding with {@value Properties#GAME_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-seriesidgames">API Docs</a></li>
     * </ul>
     *
     * @param id the id of the Series to lookup
     * @param queryParams Additional query params to filter the output, provided by {@link GamesQuery}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getGamesForSeries(String id, GamesQuery queryParams, Boolean embed) throws IOException, UnexpectedResponseException {
        return getGamesForSeries(id, queryParams, Sorting.<GamesOrderBy>builder().build(), embed);
    }

    /**
     *  GET series/{id}/games
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Game}s associated with a series, sorted by the parameters
     * defined in {@link Sorting} of type {@link GamesOrderBy}, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link GamesOrderBy} and {@link Direction}
     *     <li>Supports embedding with {@value Properties#GAME_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-seriesidgames">API Docs</a></li>
     * </ul>
     *
     * @param id the id of the Series to lookup
     * @param order Defined sorting order; {@link Sorting} of type {@link GamesOrderBy}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getGamesForSeries(String id, Sorting<GamesOrderBy> order, Boolean embed) throws IOException, UnexpectedResponseException {
        return getGamesForSeries(id, GamesQuery.builder().build(), order, embed);
    }

    /**
     *  GET series/{id}/games
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Game}s associated with a series, filtered by a set of provided
     * {@link GamesQuery} query params.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link GamesQuery}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-seriesidgames">API Docs</a></li>
     * </ul>
     *
     * @param id the id of the Series to lookup
     * @param queryParams Additional query params to filter the output, provided by {@link GamesQuery}
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getGamesForSeries(String id, GamesQuery queryParams) throws IOException, UnexpectedResponseException {
        return getGamesForSeries(id, queryParams, Sorting.<GamesOrderBy>builder().build(), false);
    }

    /**
     *  GET series/{id}/games
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Game}s associated with a series, sorted by the parameters
     * defined in {@link Sorting} of type {@link GamesOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link GamesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-seriesidgames">API Docs</a></li>
     * </ul>
     *
     * @param id the id of the Series to lookup
     * @param order Defined sorting order; {@link Sorting} of type {@link GamesOrderBy}
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getGamesForSeries(String id, Sorting<GamesOrderBy> order) throws IOException, UnexpectedResponseException {
        return getGamesForSeries(id, GamesQuery.builder().build(), order, false);
    }

    /**
     *  GET series/{id}/games
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Game}s associated with a series and optionally enriched
     * with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports embedding with {@value Properties#GAME_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-seriesidgames">API Docs</a></li>
     * </ul>
     *
     * @param id the id of the Series to lookup
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getGamesForSeries(String id, Boolean embed) throws IOException, UnexpectedResponseException {
        return getGamesForSeries(id, GamesQuery.builder().build(), Sorting.<GamesOrderBy>builder().build(), embed);
    }

    /**
     *  GET series/{id}/games
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Game}s associated with a series.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-seriesidgames">API Docs</a></li>
     * </ul>
     *
     * @param id the id of the Series to lookup
     * @return a {@link PagedResponse} of {@link Series} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getGamesForSeries(String id) throws IOException, UnexpectedResponseException {
        return getGamesForSeries(id, GamesQuery.builder().build(), Sorting.<GamesOrderBy>builder().build(), false);
    }


    /**
     *  GET series/{id}/games Bulk
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link BulkGame}s associated with a series, filtered by a set of provided
     * {@link GamesQuery} query params, sorted by the parameters defined in {@link Sorting} of type
     * {@link GamesOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link GamesQuery}</li>
     *     <li>Supports Order by and Direction in {@link GamesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-seriesidgames">API Docs</a></li>
     * </ul>
     *
     * @param id the id of the Series to lookup
     * @param queryParams Additional query params to filter the output, provided by {@link GamesQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link GamesOrderBy}
     * @return a {@link PagedResponse} of {@link BulkGame} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<BulkGame> getGamesForSeriesBulk(String id, GamesQuery queryParams, Sorting<GamesOrderBy> order) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, SERIES_PATH, id, GAME_PATH), getBulkEmbed(), queryParams.getQueryMap(), order.getQueryMap());
        return mapper.readValue(node.toString(), new TypeReference<PagedResponse<BulkGame>>() {});
    }

    /**
     *  GET series/{id}/games Bulk
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link BulkGame}s associated with a series, filtered by a set of provided
     * {@link GamesQuery} query params.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link GamesQuery}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-seriesidgames">API Docs</a></li>
     * </ul>
     *
     * @param id the id of the Series to lookup
     * @param queryParams Additional query params to filter the output, provided by {@link GamesQuery}
     * @return a {@link PagedResponse} of {@link BulkGame} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<BulkGame> getGamesForSeriesBulk(String id, GamesQuery queryParams) throws IOException, UnexpectedResponseException {
        return getGamesForSeriesBulk(id, queryParams, Sorting.<GamesOrderBy>builder().build());
    }

    /**
     *  GET series/{id}/games Bulk
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link BulkGame}s associated with a series, sorted by the
     * parameters defined in {@link Sorting} of type {@link GamesOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link GamesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-seriesidgames">API Docs</a></li>
     * </ul>
     *
     * @param id the id of the Series to lookup
     * @param order Defined sorting order; {@link Sorting} of type {@link GamesOrderBy}
     * @return a {@link PagedResponse} of {@link BulkGame} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<BulkGame> getGamesForSeriesBulk(String id, Sorting<GamesOrderBy> order) throws IOException, UnexpectedResponseException {
        return getGamesForSeriesBulk(id, GamesQuery.builder().build(), order);
    }

    /**
     *  GET series/{id}/games Bulk
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link BulkGame}s associated with a series.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-seriesidgames">API Docs</a></li>
     * </ul>
     *
     * @param id the id of the Series to lookup
     * @return a {@link PagedResponse} of {@link BulkGame} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<BulkGame> getGamesForSeriesBulk(String id) throws IOException, UnexpectedResponseException {
        return getGamesForSeriesBulk(id, GamesQuery.builder().build(), Sorting.<GamesOrderBy>builder().build());
    }

}
