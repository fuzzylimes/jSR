package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.common.Properties;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.SeriesOrderBy;
import com.fuzzylimes.jsr.query_parameters.SeriesQuery;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Series;
import com.fuzzylimes.jsr.resources.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;

public class SeriesClient {

    private ObjectMapper mapper;
    private OkHttpClient client;
    private TypeReference<PagedResponse<Series>> typeReference;

    public SeriesClient(OkHttpClient client) {
        this.mapper = new ObjectMapper();
        this.client = client;
        this.typeReference = new TypeReference<PagedResponse<Series>>() {};
    }

    /**
     *  GET series
     *
     *  Returns a list of {@link Series} objects
     *  Supports embed of moderators ({@link User} object)
     *  Supports query defined in {@link SeriesQuery}
     *  Supports Order by and Direction in {@link SeriesOrderBy} and {@link Direction}
     *  https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-series
     *
     * @return A {@link PagedResponse} of {@link Series}
     * @throws IOException if unable to retrieve response or parse response
     */
    public PagedResponse<Series> getSeries() throws IOException {
        URL url = new URL(Properties.BASE_RESOURCE + Properties.SERIES_PATH);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", Properties.USER_AGENT)
                .build();

        Response response = client.newCall(request).execute();
        JsonNode node = mapper.readTree(response.body().string());
        return mapper.readValue(node.toString(), typeReference);
    }

    /**
     *  GET series/{id}
     *
     *  Returns a {@link Series} object
     *  https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-seriesid
     *
     * @param id the id of the Series to lookup
     * @return A {@link Series}
     * @throws IOException if unable to retrieve response or parse response
     */
    public Series getSeriesById(String id) throws IOException {
        URL url = new URL(Properties.BASE_RESOURCE + Properties.SERIES_PATH + "/" + id);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", Properties.USER_AGENT)
                .build();

        Response response = client.newCall(request).execute();
        JsonNode node = mapper.readTree(response.body().string());
        return mapper.readValue(node.get("data").toString(), Series.class);
    }

    // GET series/{id}/games
    // Returns a list of Game objects
    // Supports all filters as Games resource
    // Supports all sorting options as Games resource
    // Supports full embed list as Games resource
    // Supports bulk mode
    // https://github.com/speedruncomorg/api/blob/master/version1/series.md#get-seriesidgames

}
