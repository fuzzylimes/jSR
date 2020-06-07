package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.common.Properties;
import com.fuzzylimes.jsr.resources.Guest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;

public class GuestClient {
    private ObjectMapper mapper;
    private OkHttpClient client;

    public GuestClient(OkHttpClient client) {
        this.mapper = new ObjectMapper();
        this.client = client;
    }

    /**
     * GET guests/{name}
     *
     * Returns a single Guest object
     * https://github.com/speedruncomorg/api/blob/master/version1/guests.md#get-guestsname
     *
     * @param name name of the guest to be retrieved
     * @return {@link Guest} object
     * @throws IOException if unable to retrieve response or parse response
     */
    public Guest getGuestByName(String name) throws IOException {
        URL url = new URL(Properties.BASE_RESOURCE + Properties.GUESTS_PATH + "/" + name);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", Properties.USER_AGENT)
                .build();

        Response response = client.newCall(request).execute();
        JsonNode node = mapper.readTree(response.body().string());
        return mapper.readValue(node.get("data").toString(), Guest.class);
    }

}
