package com.fuzzylimes.jsr.clients;

import com.fuzzylimes.jsr.query_parameters.sorting.GameTypesOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.GameType;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrl;
import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrlAndQueryParams;

class GameTypesClientTest {

    @Test
    void getGameTypes_SortingTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GameTypes.json");

        Sorting<GameTypesOrderBy> order = Sorting.<GameTypesOrderBy>builder()
                .direction(Direction.ASCCENDING).orderBy(GameTypesOrderBy.NAME).build();
        PagedResponse<GameType> var = GameTypesClient.getGameTypes(order);
        Assertions.assertEquals(12, var.getPagination().getSize());
        Assertions.assertEquals("4xm721op", var.getResourceList().get(10).getId());
    }

    @Test
    void getGameTypesTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GameTypes.json");

        PagedResponse<GameType> var = GameTypesClient.getGameTypes();
        Assertions.assertEquals(12, var.getPagination().getSize());
        Assertions.assertEquals("4xm721op", var.getResourceList().get(10).getId());
    }

    @Test
    void getGameTypesByIdTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrl("/responses/GameTypesById.json");

        GameType var = GameTypesClient.getGameTypesById("lyn97m9o");
        Assertions.assertEquals(2, var.getLinks().size());
        Assertions.assertEquals("lyn97m9o", var.getId());
    }
}