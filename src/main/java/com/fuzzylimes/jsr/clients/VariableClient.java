package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.JsrClient;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.Variable;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

/**
 * <p>This client is used to make requests to the /variables set of resources on SpeedRun.com's API. The official documentation
 * for this set of APIs can be found in <a href="https://github.com/speedruncomorg/api/blob/master/version1/variables.md">the API Docs</a>.</p>
 *
 * <p>Variables are custom criteria to distinguish between runs done in the same category or level. The speed in
 * Mario Kart games (which can be 50cc, 100cc or 150cc) is an example for a variable that has 3 possible values.</p>
 *
 * <p>Variables are defined per-game and can be applicable to either all runs for this game or just full-game or
 * individual-level (IL) runs. Variables can also be restricted to a category. It is therefore important to understand
 * how to get the correct set of variables:</p>
 * <ul>
 *     <li>Use {@link GameClient}s {@link GameClient#getVariablesForGame(String, Sorting)} to get all defined variables
 *     of that game, no matter how they are configured.</li>
 *     <li>Use {@link GameClient}s {@link CategoryClient#getVariablesForCategory(String, Sorting)} to only get the
 *     variables that apply to the given category.</li>
 *     <li>Use {@link GameClient}s {@link LevelsClient#getVaribaleForLevelId(String, Sorting)} to only get the
 *     variables that apply to the given level.</li>
 * </ul>
 *
 * <p>The client uses static methods for all of the resource calls, so there is now need to initialize
 * anything to make a request. Simply reference the resource you wish to use to retrieve a related pojo.</p>
 */
public class VariableClient {

    /**
     * Should not be initialized. Use the static references to each resource call
     */
    private VariableClient() {
        // Util method
    }

    /**
     * GET variables/{id}
     *
     * <p>Used to retrieve a single {@link Variable} record by its id</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/variables.md#get-variablesid">API Docs</a></li>
     * </ul>
     *
     * @param id the id of the variable to query
     * @return {@link Variable} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Variable getVariableById(String id) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, VARIABLES_PATH, id));
        return mapper.readValue(node.get("data").toString(), Variable.class);
    }

}
