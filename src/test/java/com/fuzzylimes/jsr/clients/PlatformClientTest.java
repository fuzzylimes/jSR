package com.fuzzylimes.jsr.clients;

import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.PlatformsOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Platform;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrl;
import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrlAndQueryParams;

class PlatformClientTest {

    @Test
    void getPlatformsTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Platforms.json");

        PagedResponse<Platform> var = PlatformClient.getPlatforms();
        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

    @Test
    void getPlatforms_SortingTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Platforms.json");

        Sorting<PlatformsOrderBy> order = Sorting.<PlatformsOrderBy>builder().direction(Direction.ASCCENDING).orderBy(PlatformsOrderBy.NAME).build();
        PagedResponse<Platform> var = PlatformClient.getPlatforms(order);
        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

    @Test
    void getPlatformByIdTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrl("/responses/PlatformsById.json");

        Platform var = PlatformClient.getPlatformsById("jm9577eo");
        Assertions.assertEquals("jm9577eo", var.getId());
        Assertions.assertEquals(3, var.getLinks().size());
    }


}