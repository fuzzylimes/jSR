package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fuzzylimes.jsr.JsrClient;
import com.fuzzylimes.jsr.resources.Guest;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;

import java.io.IOException;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

/**
 * <p>This client is used to make requests to the /guests set of resources on SpeedRun.com's API. The official documentation
 * for this set of APIs can be found in <a href="https://github.com/speedruncomorg/api/blob/master/version1/guests.md">the API Docs</a>.</p>
 *
 * <p>Sometimes, speedrun.com has runs done by players that have no account on the site yet. These runners are called
 * "guests" in the API. Except for a name, there is nothing we know about them.</p>
 *
 * <p>The client uses static methods for all of the resource calls, so there is now need to initialize
 * anything to make a request. Simply reference the resource you wish to use to retrieve a related pojo.</p>
 */
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
        JsonNode node = getSyncQuery(JsrClient.buildPath(BASE_RESOURCE, GUESTS_PATH, name));
        return mapper.readValue(node.get("data").toString(), Guest.class);
    }

}
