package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.resources.Guest;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

public class GuestClient {

    /**
     * Should not be initialized. Use the static references to each resource call
     */
    private GuestClient() {
        // Util method
    }

    /**
     * GET guests/{name}
     *
     * <p>Allows you to query for a guest by their guest name. Guests are non-registered users, where
     * a record was created for them on behalf of someone else.</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/guests.md#get-guestsname">API Docs</a></li>
     * </ul>
     *
     * @param name name of the guest to be retrieved
     * @return {@link Guest} object
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static Guest getGuestByName(String name) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(buildPath(BASE_RESOURCE, GUESTS_PATH, name));
        return mapper.readValue(node.get("data").toString(), Guest.class);
    }

}
