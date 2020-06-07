package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.common.Properties;
import com.fuzzylimes.jsr.sorting.Direction;
import com.fuzzylimes.jsr.sorting.PlatformOrderBy;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Platform;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;

public class PlatformClient {

    private ObjectMapper mapper;
    private OkHttpClient client;
    private TypeReference<PagedResponse<Platform>> platformsType;

    public PlatformClient(OkHttpClient client) {
        this.mapper = new ObjectMapper();
        this.client = client;
        this.platformsType = new TypeReference<PagedResponse<Platform>>() {};
    }

    /**
     * GET platforms
     *
     * Returns a list of {@link Platform} objects
     * Supports OrderBy and Direction using {@link PlatformOrderBy} and {@link Direction}
     * https://github.com/speedruncomorg/api/blob/master/version1/platforms.md#get-platforms
     *
     * @return Platforms object with list of Platforms and pagination
     * @throws IOException if unable to retrieve response or parse response
     */
    public PagedResponse<Platform> getPlatforms() throws IOException {
        URL url = new URL(Properties.BASE_RESOURCE + Properties.PLATFORMS_PATH);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", Properties.USER_AGENT)
                .build();

        Response response = client.newCall(request).execute();
        JsonNode node = mapper.readTree(response.body().string());
        return mapper.readValue(node.toString(), platformsType);
    }


    /**
     * GET platforms/{id}
     * Returns a single {@link Platform} object
     * https://github.com/speedruncomorg/api/blob/master/version1/platforms.md#get-platformsid
     *
     * @param id id of Platform to query
     * @return {@link Platform} object
     * @throws IOException if unable to retrieve response or parse response
     */

    public Platform getPlatformsById(String id) throws IOException {
        URL url = new URL(Properties.BASE_RESOURCE + Properties.PLATFORMS_PATH + "/" + id);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", Properties.USER_AGENT)
                .build();

        Response response = client.newCall(request).execute();
        JsonNode node = mapper.readTree(response.body().string());
        return mapper.readValue(node.get("data").toString(), Platform.class);
    }
}
