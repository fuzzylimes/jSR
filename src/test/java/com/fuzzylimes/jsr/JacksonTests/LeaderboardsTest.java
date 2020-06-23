package com.fuzzylimes.jsr.JacksonTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.resources.Leaderboard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class LeaderboardsTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserializeLeaderboardsByGameByCategoryTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/LeaderboardByGameByCategory.json"));
        Leaderboard var = mapper.readValue(node.get("data").toString(), Leaderboard.class);

        Assertions.assertEquals("xldev513", var.getGame().getId());
        Assertions.assertEquals(1, var.getRuns().get(0).getPlace());
        Assertions.assertEquals("rklg3rdn", var.getRuns().get(0).getRun().getCategory().getId());
        Assertions.assertEquals("v18q6wjn", var.getRuns().get(0).getRun().getStatus().getExaminer());
    }

    @Test
    public void deserializeLeaderboardsByGameByCategory_EmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/LeaderboardByGameByCategory_Embed.json"));
        Leaderboard var = mapper.readValue(node.get("data").toString(), Leaderboard.class);

        Assertions.assertEquals("xldev513", var.getGame().getGameEmbed().getId());
        Assertions.assertEquals(1, var.getRuns().get(0).getPlace());
        Assertions.assertEquals("rklg3rdn", var.getCategory().getCategoryEmbed().getId());
        Assertions.assertEquals("v18q6wjn", var.getRuns().get(0).getRun().getStatus().getExaminer());
    }

    @Test
    public void deserializeLeaderboardsByGameByLevelByCategoryTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/LeaderboardByGameByLevelByCategory.json"));
        Leaderboard var = mapper.readValue(node.get("data").toString(), Leaderboard.class);

        Assertions.assertEquals("pdv0x91w", var.getGame().getId());
        Assertions.assertEquals(1, var.getRuns().get(0).getPlace());
        Assertions.assertEquals("7dgrergk", var.getRuns().get(0).getRun().getCategory().getId());
        Assertions.assertEquals("y8d4l798", var.getRuns().get(0).getRun().getStatus().getExaminer());
    }

    @Test
    public void deserializeLeaderboardsByGameByLevelByCategory_EmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/LeaderboardByGameByLevelByCategory_Embed.json"));
        Leaderboard var = mapper.readValue(node.get("data").toString(), Leaderboard.class);

        Assertions.assertEquals("pdv0x91w", var.getGame().getGameEmbed().getId());
        Assertions.assertEquals(1, var.getRuns().get(0).getPlace());
        Assertions.assertEquals("7dgrergk", var.getCategory().getCategoryEmbed().getId());
        Assertions.assertEquals("y8d4l798", var.getRuns().get(0).getRun().getStatus().getExaminer());
    }
}
