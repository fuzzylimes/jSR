package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.JsrClient;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;

/**
 * Used for making generic requests to the speedrun.com APIs. This is currently used to handle navigation linking
 */
public class GenericClient {

    /**
     * Allows for the next or previous page of a PagedResponse to be collected.
     *
     * @param url full url to the next or previous page
     * @param type type of entity that is being collected
     * @param <T> type of entity that is being collected
     * @return the next or previous page of the response
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static <T> PagedResponse<T> getGenericPagedResponse(String url, Class<T> type) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(JsrClient.buildPath(url));
        JavaType mapping = mapper.getTypeFactory().constructParametricType(PagedResponse.class, type);
        PagedResponse<T> tPagedResponse = mapper.readValue(node.toString(), mapping);
        tPagedResponse.setType(type);
        return tPagedResponse;
    }

    private GenericClient() {
        // util class
    }
}
