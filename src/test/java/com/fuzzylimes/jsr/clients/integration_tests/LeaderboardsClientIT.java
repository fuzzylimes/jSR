package com.fuzzylimes.jsr.clients.integration_tests;

import com.fuzzylimes.jsr.clients.LeaderboardsClient;
import com.fuzzylimes.jsr.query_parameters.LeaderboardQuery;
import com.fuzzylimes.jsr.resources.Leaderboard;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Disabled
class LeaderboardsClientIT {

    @Test
    void getLeaderboardForGameCategoryQueryParams_EmbedIT() throws IOException, UnexpectedResponseException {
        LeaderboardQuery query = LeaderboardQuery.builder().emulators(false).build();
        Leaderboard var = LeaderboardsClient.getLeaderboardForGameCategory("xldev513", "rklg3rdn", true, query);
        Assertions.assertEquals("xldev513", var.getGame().getGameEmbed().getId());
        Assertions.assertEquals(1, var.getRuns().get(0).getPlace());
        Assertions.assertEquals("rklg3rdn", var.getCategory().getCategoryEmbed().getId());
        Assertions.assertEquals("v18q6wjn", var.getRuns().get(0).getRun().getStatus().getExaminer());
    }


    @Test
    void getLeaderboardForGameLevelCategoryQueryParams_EmbedIT() throws IOException, UnexpectedResponseException {
        LeaderboardQuery query = LeaderboardQuery.builder().emulators(false).build();
        Leaderboard var = LeaderboardsClient.getLeaderboardForGameLevelCategory("pdv0x91w", "5d74ypvd", "7dgrergk", true, query);
        Assertions.assertEquals("pdv0x91w", var.getGame().getGameEmbed().getId());
        Assertions.assertEquals(1, var.getRuns().get(0).getPlace());
        Assertions.assertEquals("7dgrergk", var.getCategory().getCategoryEmbed().getId());
        Assertions.assertEquals("y8d4l798", var.getRuns().get(0).getRun().getStatus().getExaminer());
    }
}