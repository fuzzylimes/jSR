package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.JsrClient;
import com.fuzzylimes.jsr.common.Properties;
import com.fuzzylimes.jsr.query_parameters.CategoryRecordsQuery;
import com.fuzzylimes.jsr.query_parameters.GamesQuery;
import com.fuzzylimes.jsr.query_parameters.LeaderboardQuery;
import com.fuzzylimes.jsr.query_parameters.sorting.*;
import com.fuzzylimes.jsr.resources.BulkGame;
import com.fuzzylimes.jsr.resources.Category;
import com.fuzzylimes.jsr.resources.Game;
import com.fuzzylimes.jsr.resources.Leaderboard;
import com.fuzzylimes.jsr.resources.Level;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Variable;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;
import java.util.List;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

/**
 * <p>This client is used to make requests to the /games set of resources on SpeedRun.com's API. The official documentation
 * for this set of APIs can be found in <a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md">the API Docs</a>.</p>
 *
 * <p>Games are the thing that runs are being done against. They are exactly what they are called - the video game that
 * is being played.</p>
 **
 * <p>The client uses static methods for all of the resource calls, so there is now need to initialize
 * anything to make a request. Simply reference the resource you wish to use to retrieve a related pojo.</p>
 */
public class GameClient {

    /**
     * Should not be initialized. Use the static references to each resource call
     */
    private GameClient() {
        // Util method
    }

    // Get games
    /**
     *  <p>GET games</p>
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Game} objects, filtered by a set of provided
     * {@link GamesQuery} query params, sorted by the parameters defined in {@link Sorting} of type
     * {@link GamesOrderBy}, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link GamesQuery}</li>
     *     <li>Supports Order by and Direction in {@link GamesOrderBy} and {@link Direction}
     *     <li>Supports embedding with {@value Properties#GAME_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-games">API Docs</a></li>
     * </ul>
     *
     * @param queryParams Additional query params to filter the output, provided by {@link GamesQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link GamesOrderBy}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Game} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getGames(GamesQuery queryParams, Sorting<GamesOrderBy> order, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GAME_PATH), getGameEmbed(), queryParams.getQueryMap(), order.getQueryMap()):
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GAME_PATH), queryParams.getQueryMap(), order.getQueryMap());
        return mapper.readValue(node.toString(), new TypeReference<PagedResponse<Game>>() {});
    }

    /**
     *  <p>GET games</p>
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Game} objects, filtered by a set of provided
     * {@link GamesQuery} query params, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link GamesQuery}</li>
     *     <li>Supports embedding with {@value Properties#GAME_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-games">API Docs</a></li>
     * </ul>
     *
     * @param queryParams Additional query params to filter the output, provided by {@link GamesQuery}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Game} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getGames(GamesQuery queryParams, Boolean embed) throws IOException, UnexpectedResponseException {
        return getGames(queryParams, Sorting.<GamesOrderBy>builder().build(), embed);
    }

    /**
     *  <p>GET games</p>
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Game} objects, sorted by the parameters defined in
     * {@link Sorting} of type {@link GamesOrderBy}, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link GamesOrderBy} and {@link Direction}
     *     <li>Supports embedding with {@value Properties#GAME_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-games">API Docs</a></li>
     * </ul>
     *
     * @param order Defined sorting order; {@link Sorting} of type {@link GamesOrderBy}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Game} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getGames(Sorting<GamesOrderBy> order, Boolean embed) throws IOException, UnexpectedResponseException {
        return getGames(GamesQuery.builder().build(), order, embed);
    }

    /**
     *  <p>GET games</p>
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Game} objects, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports embedding with {@value Properties#GAME_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-games">API Docs</a></li>
     * </ul>
     *
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Game} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getGames(Boolean embed) throws IOException, UnexpectedResponseException {
        return getGames(GamesQuery.builder().build(), Sorting.<GamesOrderBy>builder().build(), embed);
    }

    /**
     *  <p>GET games</p>
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Game} objects, filtered by a set of provided
     * {@link GamesQuery} query params, sorted by the parameters defined in {@link Sorting} of type
     * {@link GamesOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link GamesQuery}</li>
     *     <li>Supports Order by and Direction in {@link GamesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-games">API Docs</a></li>
     * </ul>
     *
     * @param queryParams Additional query params to filter the output, provided by {@link GamesQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link GamesOrderBy}
     * @return a {@link PagedResponse} of {@link Game} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getGames(GamesQuery queryParams, Sorting<GamesOrderBy> order) throws IOException, UnexpectedResponseException {
        return getGames(queryParams, order, false);
    }

    /**
     *  <p>GET games</p>
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Game} objects, filtered by a set of provided
     * {@link GamesQuery} query params.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link GamesQuery}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-games">API Docs</a></li>
     * </ul>
     *
     * @param queryParams Additional query params to filter the output, provided by {@link GamesQuery}
     * @return a {@link PagedResponse} of {@link Game} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getGames(GamesQuery queryParams) throws IOException, UnexpectedResponseException {
        return getGames(queryParams, Sorting.<GamesOrderBy>builder().build(), false);
    }

    /**
     *  <p>GET games</p>
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Game} objects, sorted by the parameters defined in
     * {@link Sorting} of type {@link GamesOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link GamesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-games">API Docs</a></li>
     * </ul>
     *
     * @param order Defined sorting order; {@link Sorting} of type {@link GamesOrderBy}
     * @return a {@link PagedResponse} of {@link Game} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getGames(Sorting<GamesOrderBy> order) throws IOException, UnexpectedResponseException {
        return getGames(GamesQuery.builder().build(), order, false);
    }

    /**
     *  <p>GET games</p>
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Game} objects.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-games">API Docs</a></li>
     * </ul>
     *
     * @return a {@link PagedResponse} of {@link Game} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getGames() throws IOException, UnexpectedResponseException {
        return getGames(GamesQuery.builder().build(), Sorting.<GamesOrderBy>builder().build(), false);
    }

    // Get games Bulk - paged response of bulk games
    /**
     *  GET games Bulk
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link BulkGame}s, filtered by a set of provided
     * {@link GamesQuery} query params, sorted by the parameters defined in {@link Sorting} of type
     * {@link GamesOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link GamesQuery}</li>
     *     <li>Supports Order by and Direction in {@link GamesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-games">API Docs</a></li>
     * </ul>
     *
     * @param queryParams Additional query params to filter the output, provided by {@link GamesQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link GamesOrderBy}
     * @return a {@link PagedResponse} of {@link BulkGame} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<BulkGame> getGamesBulk(GamesQuery queryParams, Sorting<GamesOrderBy> order) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GAME_PATH), getBulkEmbed(), queryParams.getQueryMap(), order.getQueryMap());
        return mapper.readValue(node.toString(), new TypeReference<PagedResponse<BulkGame>>() {});
    }

    /**
     *  GET games Bulk
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link BulkGame}s, filtered by a set of provided
     * {@link GamesQuery} query params.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link GamesQuery}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-games">API Docs</a></li>
     * </ul>
     *
     * @param queryParams Additional query params to filter the output, provided by {@link GamesQuery}
     * @return a {@link PagedResponse} of {@link BulkGame} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<BulkGame> getGamesBulk(GamesQuery queryParams) throws IOException, UnexpectedResponseException {
        return getGamesBulk(queryParams, Sorting.<GamesOrderBy>builder().build());
    }

    /**
     *  GET games Bulk
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link BulkGame}s, sorted by the
     * parameters defined in {@link Sorting} of type {@link GamesOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link GamesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-games">API Docs</a></li>
     * </ul>
     *
     * @param order Defined sorting order; {@link Sorting} of type {@link GamesOrderBy}
     * @return a {@link PagedResponse} of {@link BulkGame} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<BulkGame> getGamesBulk(Sorting<GamesOrderBy> order) throws IOException, UnexpectedResponseException {
        return getGamesBulk(GamesQuery.builder().build(), order);
    }

    /**
     *  GET games Bulk
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link BulkGame}s.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-games">API Docs</a></li>
     * </ul>
     *
     * @return a {@link PagedResponse} of {@link BulkGame} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<BulkGame> getGamesBulk() throws IOException, UnexpectedResponseException {
        return getGamesBulk(GamesQuery.builder().build(), Sorting.<GamesOrderBy>builder().build());
    }

    // Get games by Id
    /**
     *  <p>GET games/{id}</p>
     *
     * <p>Used to retrieve a specific {@link Game} record by Id, optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports embedding with {@value Properties#GAME_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesid">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link Game} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Game getGamesById(String id, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GAME_PATH, id), getGameEmbed()):
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GAME_PATH, id));
        return mapper.readValue(node.get("data").toString(), Game.class);
    }

    /**
     *  <p>GET games/{id}</p>
     *
     * <p>Used to retrieve a specific {@link Game} record by Id.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesid">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @return a {@link Game} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Game getGamesById(String id) throws IOException, UnexpectedResponseException {
        return getGamesById(id, false);
    }

    // Get games/{id}/categories
    /**
     *  <p>GET games/{id}/categories</p>
     *
     * <p>Used to retrieve a {@link List} of {@link Category} objects, filtered by a set of provided
     * {@link CategoryRecordsQuery} query params, sorted by the parameters defined in {@link Sorting} of type
     * {@link CategoriesOrderBy}, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link CategoryRecordsQuery}</li>
     *     <li>Supports Order by and Direction in {@link CategoriesOrderBy} and {@link Direction}
     *     <li>Supports embedding with {@value Properties#CATEGORY_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidcategories">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param queryParams Additional query params to filter the output, provided by {@link CategoryRecordsQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link CategoriesOrderBy}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link List} of {@link Category} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Category> getCategoriesForGame(String id, CategoryRecordsQuery queryParams, Sorting<CategoriesOrderBy> order, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GAME_PATH, id, CATEGORIES_PATH), getCategoryEmbed(), queryParams.getQueryMap(), order.getQueryMap()):
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GAME_PATH, id, CATEGORIES_PATH), queryParams.getQueryMap(), order.getQueryMap());
        return mapper.readValue(node.get("data").toString(), new TypeReference<List<Category>>() {});
    }

    /**
     *  <p>GET games/{id}/categories</p>
     *
     * <p>Used to retrieve a {@link List} of {@link Category} objects, filtered by a set of provided
     * {@link CategoryRecordsQuery} query params, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link CategoryRecordsQuery}</li>
     *     <li>Supports embedding with {@value Properties#CATEGORY_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidcategories">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param queryParams Additional query params to filter the output, provided by {@link CategoryRecordsQuery}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link List} of {@link Category} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Category> getCategoriesForGame(String id, CategoryRecordsQuery queryParams, Boolean embed) throws IOException, UnexpectedResponseException {
        return getCategoriesForGame(id, queryParams, Sorting.<CategoriesOrderBy>builder().build(), embed);
    }

    /**
     *  <p>GET games/{id}/categories</p>
     *
     * <p>Used to retrieve a {@link List} of {@link Category} objects, sorted by the parameters defined in
     * {@link Sorting} of type {@link CategoriesOrderBy}, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link CategoriesOrderBy} and {@link Direction}
     *     <li>Supports embedding with {@value Properties#CATEGORY_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidcategories">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param order Defined sorting order; {@link Sorting} of type {@link CategoriesOrderBy}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link List} of {@link Category} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Category> getCategoriesForGame(String id, Sorting<CategoriesOrderBy> order, Boolean embed) throws IOException, UnexpectedResponseException {
        return getCategoriesForGame(id, CategoryRecordsQuery.builder().build(), order, embed);
    }

    /**
     *  <p>GET games/{id}/categories</p>
     *
     * <p>Used to retrieve a {@link List} of {@link Category} objects, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports embedding with {@value Properties#CATEGORY_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidcategories">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link List} of {@link Category} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Category> getCategoriesForGame(String id, Boolean embed) throws IOException, UnexpectedResponseException {
        return getCategoriesForGame(id, CategoryRecordsQuery.builder().build(), Sorting.<CategoriesOrderBy>builder().build(), embed);
    }

    /**
     *  <p>GET games/{id}/categories</p>
     *
     * <p>Used to retrieve a {@link List} of {@link Category} objects, filtered by a set of provided
     * {@link CategoryRecordsQuery} query params, sorted by the parameters defined in {@link Sorting} of type
     * {@link CategoriesOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link CategoryRecordsQuery}</li>
     *     <li>Supports Order by and Direction in {@link CategoriesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidcategories">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param queryParams Additional query params to filter the output, provided by {@link CategoryRecordsQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link CategoriesOrderBy}
     * @return a {@link List} of {@link Category} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Category> getCategoriesForGame(String id, CategoryRecordsQuery queryParams, Sorting<CategoriesOrderBy> order) throws IOException, UnexpectedResponseException {
        return getCategoriesForGame(id, queryParams, order, false);
    }

    /**
     *  <p>GET games/{id}/categories</p>
     *
     * <p>Used to retrieve a {@link List} of {@link Category} objects, filtered by a set of provided
     * {@link CategoryRecordsQuery} query params.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link CategoryRecordsQuery}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidcategories">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param queryParams Additional query params to filter the output, provided by {@link CategoryRecordsQuery}
     * @return a {@link List} of {@link Category} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Category> getCategoriesForGame(String id, CategoryRecordsQuery queryParams) throws IOException, UnexpectedResponseException {
        return getCategoriesForGame(id, queryParams, Sorting.<CategoriesOrderBy>builder().build(), false);
    }

    /**
     *  <p>GET games/{id}/categories</p>
     *
     * <p>Used to retrieve a {@link List} of {@link Category} objects, sorted by the parameters defined in
     * {@link Sorting} of type {@link CategoriesOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link CategoriesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidcategories">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param order Defined sorting order; {@link Sorting} of type {@link CategoriesOrderBy}
     * @return a {@link List} of {@link Category} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Category> getCategoriesForGame(String id, Sorting<CategoriesOrderBy> order) throws IOException, UnexpectedResponseException {
        return getCategoriesForGame(id, CategoryRecordsQuery.builder().build(), order, false);
    }

    /**
     *  <p>GET games/{id}/categories</p>
     *
     * <p>Used to retrieve a {@link List} of {@link Category} objects.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidcategories">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @return a {@link List} of {@link Category} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Category> getCategoriesForGame(String id) throws IOException, UnexpectedResponseException {
        return getCategoriesForGame(id, CategoryRecordsQuery.builder().build(), Sorting.<CategoriesOrderBy>builder().build(), false);
    }

    // Get games/{id}/levels
    /**
     *  <p>GET games/{id}/levels</p>
     *
     * <p>Used to retrieve a {@link List} of {@link Level} objects, sorted by the parameters defined in
     * {@link Sorting} of type {@link LevelsOrderBy}, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link LevelsOrderBy} and {@link Direction}
     *     <li>Supports embedding with {@value Properties#LEVEL_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidlevels">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param order Defined sorting order; {@link Sorting} of type {@link LevelsOrderBy}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link List} of {@link Level} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Level> getLevelsForGame(String id, Sorting<LevelsOrderBy> order, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GAME_PATH, id, LEVELS_PATH), getLevelEmbed(), order.getQueryMap()):
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GAME_PATH, id, LEVELS_PATH), order.getQueryMap());
        return mapper.readValue(node.get("data").toString(), new TypeReference<List<Level>>() {});
    }

    /**
     *  <p>GET games/{id}/levels</p>
     *
     * <p>Used to retrieve a {@link List} of {@link Level} objects, optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports embedding with {@value Properties#LEVEL_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidlevels">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link List} of {@link Level} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Level> getLevelsForGame(String id, Boolean embed) throws IOException, UnexpectedResponseException {
        return getLevelsForGame(id, Sorting.<LevelsOrderBy>builder().build(), embed);
    }

    /**
     *  <p>GET games/{id}/levels</p>
     *
     * <p>Used to retrieve a {@link List} of {@link Level} objects, sorted by the parameters defined in
     * {@link Sorting} of type {@link LevelsOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link LevelsOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidlevels">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param order Defined sorting order; {@link Sorting} of type {@link LevelsOrderBy}
     * @return a {@link List} of {@link Level} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Level> getLevelsForGame(String id, Sorting<LevelsOrderBy> order) throws IOException, UnexpectedResponseException {
        return getLevelsForGame(id, order, false);
    }

    /**
     *  <p>GET games/{id}/levels</p>
     *
     * <p>Used to retrieve a {@link List} of {@link Level} objects.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidlevels">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @return a {@link List} of {@link Level} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Level> getLevelsForGame(String id) throws IOException, UnexpectedResponseException {
        return getLevelsForGame(id, Sorting.<LevelsOrderBy>builder().build(), false);
    }


    // Get games/{id}/variables
    /**
     *  <p>GET games/{id}/variables</p>
     *
     * <p>Used to retrieve a {@link List} of {@link Variable} objects related to a game, sorted by the parameters defined in
     * {@link Sorting} of type {@link VariablesOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link VariablesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidvariables">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param order Defined sorting order; {@link Sorting} of type {@link VariablesOrderBy}
     * @return a {@link List} of {@link Variable} objects associated with the provided id
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Variable> getVariablesForGame(String id, Sorting<VariablesOrderBy> order) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GAME_PATH, id, VARIABLES_PATH), order.getQueryMap());
        return mapper.readValue(node.get("data").toString(), new TypeReference<List<Variable>>() {});
    }

    /**
     *  <p>GET games/{id}/variables</p>
     *
     * <p>Used to retrieve a {@link List} of {@link Variable} objects related to a game.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidvariables">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @return a {@link List} of {@link Variable} objects associated with the provided id
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<Variable> getVariablesForGame(String id) throws IOException, UnexpectedResponseException {
        return getVariablesForGame(id, Sorting.<VariablesOrderBy>builder().build());
    }

    // Get games/{id}/derived-games
    /**
     *  <p>GET games/{id}/derived-games</p>
     *
     * <p>Used to retrieve all of the games that have the given game id set as their base game. This is used specifically
     * to relate romhacks or fan games back to their original game. Will return a {@link PagedResponse} of
     * {@link Game} objects, filtered by a set of provided {@link GamesQuery} query params, sorted by the parameters
     * defined in {@link Sorting} of type {@link GamesOrderBy}, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link GamesQuery}</li>
     *     <li>Supports Order by and Direction in {@link GamesOrderBy} and {@link Direction}
     *     <li>Supports embedding with {@value Properties#GAME_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidderived-games">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param queryParams Additional query params to filter the output, provided by {@link GamesQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link GamesOrderBy}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Game} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getDerivedGames(String id, GamesQuery queryParams, Sorting<GamesOrderBy> order, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GAME_PATH, id, DERIVED_GAMES_PATH), getGameEmbed(), queryParams.getQueryMap(), order.getQueryMap()):
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GAME_PATH, id, DERIVED_GAMES_PATH), queryParams.getQueryMap(), order.getQueryMap());
        return mapper.readValue(node.toString(), new TypeReference<PagedResponse<Game>>() {});
    }

    /**
     *  <p>GET games/{id}/derived-games</p>
     *
     * <p>Used to retrieve all of the games that have the given game id set as their base game. This is used specifically
     * to relate romhacks or fan games back to their original game. Will return a {@link PagedResponse} of
     * {@link Game} objects, sorted by the parameters defined in {@link Sorting} of type {@link GamesOrderBy},
     * and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link GamesOrderBy} and {@link Direction}
     *     <li>Supports embedding with {@value Properties#GAME_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidderived-games">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param order Defined sorting order; {@link Sorting} of type {@link GamesOrderBy}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Game} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getDerivedGames(String id, Sorting<GamesOrderBy> order, Boolean embed) throws IOException, UnexpectedResponseException {
        return getDerivedGames(id, GamesQuery.builder().build(), order, embed);
    }

    /**
     *  <p>GET games/{id}/derived-games</p>
     *
     * <p>Used to retrieve all of the games that have the given game id set as their base game. This is used specifically
     * to relate romhacks or fan games back to their original game. Will return a {@link PagedResponse} of
     * {@link Game} objects, filtered by a set of provided {@link GamesQuery} query params, and optionally enriched
     * with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link GamesQuery}</li>
     *     <li>Supports embedding with {@value Properties#GAME_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidderived-games">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param queryParams Additional query params to filter the output, provided by {@link GamesQuery}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Game} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getDerivedGames(String id, GamesQuery queryParams, Boolean embed) throws IOException, UnexpectedResponseException {
        return getDerivedGames(id, queryParams, Sorting.<GamesOrderBy>builder().build(), embed);
    }

    /**
     *  <p>GET games/{id}/derived-games</p>
     *
     * <p>Used to retrieve all of the games that have the given game id set as their base game. This is used specifically
     * to relate romhacks or fan games back to their original game. Will return a {@link PagedResponse} of
     * {@link Game} objects, optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports embedding with {@value Properties#GAME_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidderived-games">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Game} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getDerivedGames(String id, Boolean embed) throws IOException, UnexpectedResponseException {
        return getDerivedGames(id, GamesQuery.builder().build(), Sorting.<GamesOrderBy>builder().build(), embed);
    }

    /**
     *  <p>GET games/{id}/derived-games</p>
     *
     * <p>Used to retrieve all of the games that have the given game id set as their base game. This is used specifically
     * to relate romhacks or fan games back to their original game. Will return a {@link PagedResponse} of
     * {@link Game} objects, filtered by a set of provided {@link GamesQuery} query params, sorted by the parameters
     * defined in {@link Sorting} of type {@link GamesOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link GamesQuery}</li>
     *     <li>Supports Order by and Direction in {@link GamesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidderived-games">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param queryParams Additional query params to filter the output, provided by {@link GamesQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link GamesOrderBy}
     * @return a {@link PagedResponse} of {@link Game} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getDerivedGames(String id, GamesQuery queryParams, Sorting<GamesOrderBy> order) throws IOException, UnexpectedResponseException {
        return getDerivedGames(id, queryParams, order, false);
    }

    /**
     *  <p>GET games/{id}/derived-games</p>
     *
     * <p>Used to retrieve all of the games that have the given game id set as their base game. This is used specifically
     * to relate romhacks or fan games back to their original game. Will return a {@link PagedResponse} of
     * {@link Game} objects, filtered by a set of provided {@link GamesQuery} query params.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link GamesQuery}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidderived-games">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param queryParams Additional query params to filter the output, provided by {@link GamesQuery}
     * @return a {@link PagedResponse} of {@link Game} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getDerivedGames(String id, GamesQuery queryParams) throws IOException, UnexpectedResponseException {
        return getDerivedGames(id, queryParams, Sorting.<GamesOrderBy>builder().build(), false);
    }

    /**
     *  <p>GET games/{id}/derived-games</p>
     *
     * <p>Used to retrieve all of the games that have the given game id set as their base game. This is used specifically
     * to relate romhacks or fan games back to their original game. Will return a {@link PagedResponse} of
     * {@link Game} objects, sorted by the parameters defined in {@link Sorting} of type {@link GamesOrderBy}.</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link GamesOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidderived-games">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param order Defined sorting order; {@link Sorting} of type {@link GamesOrderBy}
     * @return a {@link PagedResponse} of {@link Game} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getDerivedGames(String id, Sorting<GamesOrderBy> order) throws IOException, UnexpectedResponseException {
        return getDerivedGames(id, GamesQuery.builder().build(), order, false);
    }

    /**
     *  <p>GET games/{id}/derived-games</p>
     *
     * <p>Used to retrieve all of the games that have the given game id set as their base game. This is used specifically
     * to relate romhacks or fan games back to their original game. Will return a {@link PagedResponse} of
     * {@link Game} objects.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidderived-games">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @return a {@link PagedResponse} of {@link Game} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Game> getDerivedGames(String id) throws IOException, UnexpectedResponseException {
        return getDerivedGames(id, GamesQuery.builder().build(), Sorting.<GamesOrderBy>builder().build(), false);
    }

    // Get games/{id}/records
    /**
     *  <p>GET games/{id}/records</p>
     *
     * <p>Used to retrieve all of the records (first three places) for every (category,level) combination of the
     * given game. Will return a {@link PagedResponse} of {@link Leaderboard} objects, filtered by a set of provided
     * {@link LeaderboardQuery} query params, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link LeaderboardQuery}</li>
     *     <li>Supports embedding with {@value Properties#LEADERBOARD_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidrecords">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param queryParams Additional query params to filter the output, provided by {@link LeaderboardQuery}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Leaderboard} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Leaderboard> getGameRecords(String id, LeaderboardQuery queryParams, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GAME_PATH, id, RECORDS_PATH), getLeaderboardEmbed(), queryParams.getQueryMap()):
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GAME_PATH, id, RECORDS_PATH), queryParams.getQueryMap());
        return mapper.readValue(node.toString(), new TypeReference<PagedResponse<Leaderboard>>() {});
    }

    /**
     *  <p>GET games/{id}/records</p>
     *
     * <p>Used to retrieve all of the records (first three places) for every (category,level) combination of the
     * given game. Will return a {@link PagedResponse} of {@link Leaderboard} objects, optionally enriched with
     * embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports embedding with {@value Properties#LEADERBOARD_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidrecords">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link PagedResponse} of {@link Leaderboard} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Leaderboard> getGameRecords(String id, Boolean embed) throws IOException, UnexpectedResponseException {
        return getGameRecords(id, LeaderboardQuery.builder().build(), embed);
    }

    /**
     *  <p>GET games/{id}/records</p>
     *
     * <p>Used to retrieve all of the records (first three places) for every (category,level) combination of the
     * given game. Will return a {@link PagedResponse} of {@link Leaderboard} objects, filtered by a set of provided
     * {@link LeaderboardQuery} query params.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link LeaderboardQuery}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidrecords">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @param queryParams Additional query params to filter the output, provided by {@link LeaderboardQuery}
     * @return a {@link PagedResponse} of {@link Leaderboard} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Leaderboard> getGameRecords(String id, LeaderboardQuery queryParams) throws IOException, UnexpectedResponseException {
        return getGameRecords(id, queryParams, false);
    }

    /**
     *  <p>GET games/{id}/records</p>
     *
     * <p>Used to retrieve all of the records (first three places) for every (category,level) combination of the
     * given game. Will return a {@link PagedResponse} of {@link Leaderboard} objects.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/games.md#get-gamesidrecords">API Docs</a></li>
     * </ul>
     *
     * @param id the id or abbreviation of the game being queried
     * @return a {@link PagedResponse} of {@link Leaderboard} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<Leaderboard> getGameRecords(String id) throws IOException, UnexpectedResponseException {
        return getGameRecords(id, LeaderboardQuery.builder().build(), false);
    }
}
