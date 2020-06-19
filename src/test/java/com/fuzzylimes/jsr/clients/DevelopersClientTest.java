package com.fuzzylimes.jsr.clients;

import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.DevelopersOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.Developer;
import com.fuzzylimes.jsr.resources.Engine;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrl;
import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrlAndQueryParams;

class DevelopersClientTest {

    @Test
    void getDevelopers_SortingTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Developers.json");

        Sorting<DevelopersOrderBy> order = Sorting.<DevelopersOrderBy>builder()
                .direction(Direction.ASCCENDING).orderBy(DevelopersOrderBy.NAME).build();
        PagedResponse<Developer> var = DevelopersClient.getDevelopers(order);
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("yzo7mleq", var.getResourceList().get(3).getId());
    }

    @Test
    void getDevelopersTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Developers.json");

        PagedResponse<Developer> var = DevelopersClient.getDevelopers();
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("yzo7mleq", var.getResourceList().get(3).getId());
    }

    @Test
    void getDevelopersByIdTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrl("/responses/DevelopersById.json");

        Developer var = DevelopersClient.getDevelopersById("5mznpr6r");
        Assertions.assertEquals(2, var.getLinks().size());
        Assertions.assertEquals("5mznpr6r", var.getId());
    }
}