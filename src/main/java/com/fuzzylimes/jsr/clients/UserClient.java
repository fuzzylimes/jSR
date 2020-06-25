package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.JsrClient;
import com.fuzzylimes.jsr.common.Properties;
import com.fuzzylimes.jsr.query_parameters.UserPersonalBestsQuery;
import com.fuzzylimes.jsr.query_parameters.UserQuery;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.query_parameters.sorting.UsersOrderBy;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.PersonalBest;
import com.fuzzylimes.jsr.resources.User;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;
import java.util.List;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

/**
 * <p>This client is used to make requests to the /users set of resources on SpeedRun.com's API. The official documentation
 * for this set of APIs can be found in <a href="https://github.com/speedruncomorg/api/blob/master/version1/users.md">the API Docs</a>.</p>
 *
 * <p>Users are the individuals who have registered an account on speedrun.com. Users submit (their) runs and moderate
 * games, besides other things that are not covered by this API (like writing posts in the forums).</p>
 *
 * <p>The client uses static methods for all of the resource calls, so there is now need to initialize
 * anything to make a request. Simply reference the resource you wish to use to retrieve a related pojo.</p>
 */
public class UserClient {

    /**
     * Should not be initialized. Use the static references to each resource call
     */
    private UserClient() {
        // Util method
    }

    /**
     * GET users
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link User} objects, filtered by a set of provided
     * {@link UserQuery} query params, sorted by the parameters defined in {@link Sorting} of type
     * {@link UsersOrderBy}</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link UserQuery}</li>
     *     <li>Supports Order by and Direction in {@link UsersOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/users.md#get-users">API Docs</a></li>
     * </ul>
     *
     * @param queryParams Additional query params to filter the output, provided by {@link UserQuery}
     * @param order Defined sorting order; {@link Sorting} of type {@link UsersOrderBy}
     * @return a {@link PagedResponse} of {@link User} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<User> getUsers(UserQuery queryParams, Sorting<UsersOrderBy> order) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, USERS_PATH), queryParams.getQueryMap(), order.getQueryMap());
        PagedResponse<User> response = mapper.readValue(node.toString(), new TypeReference<PagedResponse<User>>() {});
        response.setType(User.class);
        return response;
    }

    /**
     * GET users
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link User} objects, sorted by the parameters defined in {@link Sorting} of type
     * {@link UsersOrderBy}</p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link UsersOrderBy} and {@link Direction}
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/users.md#get-users">API Docs</a></li>
     * </ul>
     *
     * @param order Defined sorting order; {@link Sorting} of type {@link UsersOrderBy}
     * @return a {@link PagedResponse} of {@link User} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<User> getUsers(Sorting<UsersOrderBy> order) throws IOException, UnexpectedResponseException {
        return getUsers(UserQuery.builder().build(), order);
    }

    /**
     * GET users
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link User} objects, filtered by a set of provided
     * {@link UserQuery} query params</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link UserQuery}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/users.md#get-users">API Docs</a></li>
     * </ul>
     *
     * @param queryParams Additional query params to filter the output, provided by {@link UserQuery}
     * @return a {@link PagedResponse} of {@link User} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<User> getUsers(UserQuery queryParams) throws IOException, UnexpectedResponseException {
        return getUsers(queryParams, Sorting.<UsersOrderBy>builder().build());
    }

    /**
     * GET users
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link User} objects</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/users.md#get-users">API Docs</a></li>
     * </ul>
     *
     * @return a {@link PagedResponse} of {@link User} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static PagedResponse<User> getUsers() throws IOException, UnexpectedResponseException {
        return getUsers(UserQuery.builder().build(), Sorting.<UsersOrderBy>builder().build());
    }


    /**
     * GET users/{id}
     *
     * <p>Used to retrieve a {@link User} object for a specific Id</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/users.md#get-usersid">API Docs</a></li>
     * </ul>
     *
     * @param id the id of the user to be queried
     * @return a {@link User} object for the queried Id
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static User getUserById(String id) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, USERS_PATH, id));
        return mapper.readValue(node.get("data").toString(), User.class);
    }


    /**
     * GET users/{id}/personal-bests
     *
     * <p>Used to retrieve a {@link List} of {@link PersonalBest} objects, filtered by a set of provided
     * {@link UserPersonalBestsQuery} query params, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link UserPersonalBestsQuery}</li>
     *     <li>Supports embedding with {@value Properties#RUNS_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/users.md#get-usersidpersonal-bests">API Docs</a></li>
     * </ul>
     *
     * @param userId Id of the {@link User} to collect PB runs
     * @param queryParams Additional query params to filter the output, provided by {@link UserPersonalBestsQuery}
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link List} of {@link PersonalBest} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<PersonalBest> getRunsForUser(String userId, UserPersonalBestsQuery queryParams, Boolean embed) throws IOException, UnexpectedResponseException {
        JsonNode node = Boolean.TRUE.equals(embed) ?
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, USERS_PATH, userId, PERSONAL_BESTS_PATH), getRunsEmbed(), queryParams.getQueryMap()):
                getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, USERS_PATH, userId, PERSONAL_BESTS_PATH), queryParams.getQueryMap());
        return mapper.readValue(node.get("data").toString(), new TypeReference<List<PersonalBest>>() {});
    }

    /**
     * GET users/{id}/personal-bests
     *
     * <p>Used to retrieve a {@link List} of {@link PersonalBest} objects, and optionally enriched with embedded data objects.</p>
     *
     * <ul>
     *     <li>Supports embedding with {@value Properties#RUNS_EMBED_VALUES}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/users.md#get-usersidpersonal-bests">API Docs</a></li>
     * </ul>
     *
     * @param userId Id of the {@link User} to collect PB runs
     * @param embed whether or not to embed all additional, supported, embed items
     * @return a {@link List} of {@link PersonalBest} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<PersonalBest> getRunsForUser(String userId, Boolean embed) throws IOException, UnexpectedResponseException {
        return getRunsForUser(userId, UserPersonalBestsQuery.builder().build(), embed);
    }

    /**
     * GET users/{id}/personal-bests
     *
     * <p>Used to retrieve a {@link List} of {@link PersonalBest} objects, filtered by a set of provided
     * {@link UserPersonalBestsQuery} query params.</p>
     *
     * <ul>
     *     <li>Supports query parameters available in {@link UserPersonalBestsQuery}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/users.md#get-usersidpersonal-bests">API Docs</a></li>
     * </ul>
     *
     * @param userId Id of the {@link User} to collect PB runs
     * @param queryParams Additional query params to filter the output, provided by {@link UserPersonalBestsQuery}
     * @return a {@link List} of {@link PersonalBest} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<PersonalBest> getRunsForUser(String userId, UserPersonalBestsQuery queryParams) throws IOException, UnexpectedResponseException {
        return getRunsForUser(userId, queryParams, false);
    }

    /**
     * GET users/{id}/personal-bests
     *
     * <p>Used to retrieve a {@link List} of {@link PersonalBest} objects.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/users.md#get-usersidpersonal-bests">API Docs</a></li>
     * </ul>
     *
     * @param userId Id of the {@link User} to collect PB runs
     * @return a {@link List} of {@link PersonalBest} objects
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static List<PersonalBest> getRunsForUser(String userId) throws IOException, UnexpectedResponseException {
        return getRunsForUser(userId, UserPersonalBestsQuery.builder().build(), false);
    }

}
