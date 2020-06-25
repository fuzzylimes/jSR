package com.fuzzylimes.jsr.clients.integration_tests;

import com.fuzzylimes.jsr.clients.GameClient;
import com.fuzzylimes.jsr.resources.Game;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PaginationTest {
    @Test
    void getGamesPagedTest() throws IOException, UnexpectedResponseException {

        PagedResponse<Game> page1 = GameClient.getGames();

        Assertions.assertEquals(20, page1.getPagination().getSize());
        Assertions.assertEquals("y65r071e", page1.getResourceList().get(19).getId());
        Assertions.assertEquals("2015-01-03T22:44:26Z", page1.getResourceList().get(19).getCreated());
        Assertions.assertTrue(page1.getResourceList().get(19).getRegions().getIds().contains("e6lxy1dz"));

        PagedResponse<Game> page2 = page1.getNextPage();
        Assertions.assertEquals(20, page2.getPagination().getSize());
        Assertions.assertEquals("m1mxxn36", page2.getResourceList().get(19).getId());
        Assertions.assertEquals("2017-11-29T15:26:25Z", page2.getResourceList().get(19).getCreated());
        Assertions.assertTrue(page2.getResourceList().get(19).getRegions().getIds().isEmpty());

        page1 = page2.getPreviousPage();
        Assertions.assertEquals(20, page1.getPagination().getSize());
        Assertions.assertEquals("y65r071e", page1.getResourceList().get(19).getId());
        Assertions.assertEquals("2015-01-03T22:44:26Z", page1.getResourceList().get(19).getCreated());
        Assertions.assertTrue(page1.getResourceList().get(19).getRegions().getIds().contains("e6lxy1dz"));
    }
}
