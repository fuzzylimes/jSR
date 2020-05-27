package com.fuzzylimes.jSR.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jSR.common.Properties;
import com.fuzzylimes.jSR.resources.PagedResponse;
import com.fuzzylimes.jSR.resources.Region;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;

public class RegionClient {

    private ObjectMapper mapper;
    private OkHttpClient client;
    private TypeReference<PagedResponse<Region>> typeReference;

    public RegionClient(OkHttpClient client) {
        this.mapper = new ObjectMapper();
        this.client = client;
        this.typeReference = new TypeReference<PagedResponse<Region>>() {};
    }

    /**
     * GET regions
     *
     * @return a {@link PagedResponse} of {@link Region}
     * @throws IOException if unable to retrieve response or parse response
     */
    public PagedResponse<Region> getRegions() throws IOException {
        URL url = new URL(Properties.BASE_RESOURCE + Properties.REGION_PATH);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", Properties.USER_AGENT)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful() && response.body() != null) {
            return mapper.readValue(response.body().string(), typeReference);
        } else {
            return new PagedResponse<>();
        }

    }

    /**
     * @param id
     * @return
     * @throws IOException
     */
    public Region getRegion(String id) throws IOException {
        URL url = new URL(Properties.BASE_RESOURCE + Properties.REGION_PATH + "/" + id);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", Properties.USER_AGENT)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful() && response.body() != null) {
            JsonNode r = mapper.readTree(response.body().string());
            return mapper.readValue(r.get("data").toString(), Region.class);
        } else {
            return new Region();
        }
    }

}
