package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.common.Properties;
import com.fuzzylimes.jsr.resources.Variable;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

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
        JsonNode node = getSyncQuery(buildPath(BASE_RESOURCE, VARIABLES_PATH, id));
        return mapper.readValue(node.get("data").toString(), Variable.class);
    }

}
