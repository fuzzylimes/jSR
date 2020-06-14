package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.JsrClient;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.PlatformOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Platform;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

class PlatformClientTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void getPlatformsTest() throws IOException, UnexpectedResponseException {
        new MockUp<JsrClient>() {
            @Mock
            public JsonNode getSyncQuery(String url) throws IOException {
                return mapper.readTree(DeserializeUtil.getFile("/responses/Platforms.json"));
            }
        };

        PagedResponse<Platform> var = PlatformClient.getPlatforms();
        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

    @Test
    void getPlatforms_SortingTest() throws IOException, UnexpectedResponseException {
        new MockUp<JsrClient>() {
            @Mock
            public JsonNode getSyncQuery(String url, Map<String, String>... qParams) throws IOException {
                return mapper.readTree(DeserializeUtil.getFile("/responses/Platforms.json"));
            }
        };
        Sorting<PlatformOrderBy> order = Sorting.<PlatformOrderBy>builder().direction(Direction.ASCCENDING).orderBy(PlatformOrderBy.NAME).build();
        PagedResponse<Platform> var = PlatformClient.getPlatforms(order);
        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

    @Test
    void getPlatformByIdTest() throws IOException, UnexpectedResponseException {
        new MockUp<JsrClient>() {
            @Mock
            public JsonNode getSyncQuery(String url) throws IOException {
                return mapper.readTree(DeserializeUtil.getFile("/responses/PlatformsById.json"));
            }
        };

        Platform var = PlatformClient.getPlatformsById("jm9577eo");
        Assertions.assertEquals("jm9577eo", var.getId());
        Assertions.assertEquals(3, var.getLinks().size());
    }


    @Test
    void getPlatforms_SortingIT() throws IOException, UnexpectedResponseException {
        Sorting<PlatformOrderBy> order = Sorting.<PlatformOrderBy>builder().direction(Direction.ASCCENDING).orderBy(PlatformOrderBy.NAME).build();
        PagedResponse<Platform> var = PlatformClient.getPlatforms(order);
        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }


}