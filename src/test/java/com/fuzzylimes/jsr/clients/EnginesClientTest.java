package com.fuzzylimes.jsr.clients;

import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.EnginesOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.Engine;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrl;
import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrlAndQueryParams;

class EnginesClientTest {

    @Test
    void getEngines_SortingTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Engines.json");

        Sorting<EnginesOrderBy> order = Sorting.<EnginesOrderBy>builder()
                .direction(Direction.ASCCENDING).orderBy(EnginesOrderBy.NAME).build();
        PagedResponse<Engine> var = EnginesClient.getEngines(order);
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("p39mo81j", var.getResourceList().get(3).getId());
    }

    @Test
    void getEnginesTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Engines.json");

        PagedResponse<Engine> var = EnginesClient.getEngines();
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("p39mo81j", var.getResourceList().get(3).getId());
    }

    @Test
    void getEnginesByIdTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrl("/responses/EnginesById.json");

        Engine var = EnginesClient.getEnginesById("w3vgmn3o");
        Assertions.assertEquals(2, var.getLinks().size());
        Assertions.assertEquals("w3vgmn3o", var.getId());
    }
}