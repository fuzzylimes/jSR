package com.fuzzylimes.jsr.clients;

import com.fuzzylimes.jsr.query_parameters.LeaderboardQuery;
import com.fuzzylimes.jsr.resources.Leaderboard;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrlAndQueryParams;

class LeaderboardsClientTest {

    @Test
    void getLeaderboardForGameCategoryQueryParams_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/LeaderboardByGameByCategory_Embed.json");

        LeaderboardQuery query = LeaderboardQuery.builder().emulators(false).build();
        Leaderboard var = LeaderboardsClient.getLeaderboardForGameCategory("xldev513", "rklg3rdn", true, query);
        Assertions.assertEquals("xldev513", var.getGame().getGameEmbed().getId());
        Assertions.assertEquals(1, var.getRuns().get(0).getPlace());
        Assertions.assertEquals("rklg3rdn", var.getCategory().getCategoryEmbed().getId());
        Assertions.assertEquals("v18q6wjn", var.getRuns().get(0).getRun().getStatus().getExaminer());
    }

    @Test
    void getLeaderboardForGameCategoryQuery_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/LeaderboardByGameByCategory_Embed.json");

        Leaderboard var = LeaderboardsClient.getLeaderboardForGameCategory("xldev513", "rklg3rdn", true);
        Assertions.assertEquals("xldev513", var.getGame().getGameEmbed().getId());
        Assertions.assertEquals(1, var.getRuns().get(0).getPlace());
        Assertions.assertEquals("rklg3rdn", var.getCategory().getCategoryEmbed().getId());
        Assertions.assertEquals("v18q6wjn", var.getRuns().get(0).getRun().getStatus().getExaminer());
    }

    @Test
    void getLeaderboardForGameCategoryQueryTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/LeaderboardByGameByCategory.json");

        Leaderboard var = LeaderboardsClient.getLeaderboardForGameCategory("xldev513", "rklg3rdn");
        Assertions.assertEquals("xldev513", var.getGame().getId());
        Assertions.assertEquals(1, var.getRuns().get(0).getPlace());
        Assertions.assertEquals("rklg3rdn", var.getRuns().get(0).getRun().getCategory().getId());
        Assertions.assertEquals("v18q6wjn", var.getRuns().get(0).getRun().getStatus().getExaminer());
    }


    @Test
    void getLeaderboardForGameLevelCategoryQueryParams_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/LeaderboardByGameByLevelByCategory_Embed.json");

        LeaderboardQuery query = LeaderboardQuery.builder().emulators(false).build();
        Leaderboard var = LeaderboardsClient.getLeaderboardForGameLevelCategory("pdv0x91w", "5d74ypvd", "7dgrergk", true, query);
        Assertions.assertEquals("pdv0x91w", var.getGame().getGameEmbed().getId());
        Assertions.assertEquals(1, var.getRuns().get(0).getPlace());
        Assertions.assertEquals("7dgrergk", var.getCategory().getCategoryEmbed().getId());
        Assertions.assertEquals("y8d4l798", var.getRuns().get(0).getRun().getStatus().getExaminer());
    }

    @Test
    void getLeaderboardForGameLevelCategoryQuery_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/LeaderboardByGameByLevelByCategory_Embed.json");

        Leaderboard var = LeaderboardsClient.getLeaderboardForGameLevelCategory("pdv0x91w", "5d74ypvd", "7dgrergk", true);
        Assertions.assertEquals("pdv0x91w", var.getGame().getGameEmbed().getId());
        Assertions.assertEquals(1, var.getRuns().get(0).getPlace());
        Assertions.assertEquals("7dgrergk", var.getCategory().getCategoryEmbed().getId());
        Assertions.assertEquals("y8d4l798", var.getRuns().get(0).getRun().getStatus().getExaminer());
    }

    @Test
    void getLeaderboardForGameLevelCategoryQueryTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/LeaderboardByGameByLevelByCategory.json");

        Leaderboard var = LeaderboardsClient.getLeaderboardForGameLevelCategory("pdv0x91w", "5d74ypvd", "7dgrergk");
        Assertions.assertEquals("pdv0x91w", var.getGame().getId());
        Assertions.assertEquals(1, var.getRuns().get(0).getPlace());
        Assertions.assertEquals("7dgrergk", var.getRuns().get(0).getRun().getCategory().getId());
        Assertions.assertEquals("y8d4l798", var.getRuns().get(0).getRun().getStatus().getExaminer());
    }
}