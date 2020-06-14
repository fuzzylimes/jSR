package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.common.Properties;
import com.fuzzylimes.jsr.query_parameters.sorting.CategoriesOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.RegionsOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Region;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.fuzzylimes.jsr.JsrClient.getSyncQuery;
import static com.fuzzylimes.jsr.JsrClient.mapper;
import static com.fuzzylimes.jsr.common.Properties.*;

public class RegionClient {

    private TypeReference<PagedResponse<Region>> typeReference = new TypeReference<PagedResponse<Region>>() {};

    /**
     * GET regions
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Region} objects, sorted by the parameters defined
     * in {@link Sorting} of type {@link RegionsOrderBy} </p>
     *
     * <ul>
     *     <li>Supports Order by and Direction in {@link RegionsOrderBy} and {@link Direction}</li>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/regions.md#get-regions">API Docs</a></li>
     * </ul>
     *
     * @param sorting Defined sorting order; {@link Sorting} of type {@link RegionsOrderBy}
     * @return a {@link PagedResponse} of {@link Region}
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public PagedResponse<Region> getRegions(Sorting<RegionsOrderBy> sorting) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(buildPath(BASE_RESOURCE, REGION_PATH), sorting.getQueryMap());
        return mapper.readValue(node.toString(), typeReference);
    }

    /**
     * GET regions
     *
     * <p>Used to retrieve a {@link PagedResponse} of {@link Region} objects</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/regions.md#get-regions">API Docs</a></li>
     * </ul>
     *
     * @return a {@link PagedResponse} of {@link Region}
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public PagedResponse<Region> getRegions() throws IOException, UnexpectedResponseException {
        return getRegions(Sorting.<RegionsOrderBy>builder().build());
    }


    /**
     * GET regions/{id}
     *
     * <p>Used to retrieve a specific {@link Region}</p>
     *
     * <ul>
     *     <li><a href="https://github.com/speedruncomorg/api/blob/master/version1/regions.md#get-regionsid">API Docs</a></li>
     * </ul>
     *
     * @param regionId Id of {@link Region} to be queried
     * @return a {@link Region}
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public Region getRegionById(String regionId) throws IOException, UnexpectedResponseException {
        JsonNode node = getSyncQuery(buildPath(BASE_RESOURCE, REGION_PATH, regionId));
        return mapper.readValue(node.get("data").toString(), Region.class);
    }

}
