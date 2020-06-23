package com.fuzzylimes.jsr.JacksonTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.resources.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class GamesTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void deserializeGamesTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Games.json"));
        PagedResponse<Game> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Game>>() {});

        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("y65r071e", var.getResourceList().get(19).getId());
        Assertions.assertEquals("2015-01-03T22:44:26Z", var.getResourceList().get(19).getCreated());
        Assertions.assertTrue(var.getResourceList().get(19).getRegions().getIds().contains("e6lxy1dz"));
    }

    @Test
    void deserializeGames_EmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Games_Embed.json"));
        PagedResponse<Game> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Game>>() {});

        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("y65r071e", var.getResourceList().get(19).getId());
        Assertions.assertEquals("2015-01-03T22:44:26Z", var.getResourceList().get(19).getCreated());
        Assertions.assertEquals("o316x197", var.getResourceList().get(19).getRegions().getEmbeddedRegions().get(2).getId());
    }

    @Test
    void deserializeGames_BulkTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Games_Bulk.json"));
        PagedResponse<BulkGame> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<BulkGame>>() {});

        Assertions.assertEquals(250, var.getPagination().getSize());
        Assertions.assertEquals("y6544k0d", var.getResourceList().get(249).getId());
        Assertions.assertEquals("A Rose in the Twilight", var.getResourceList().get(249).getNames().getInternational());
    }

    @Test
    void deserializeGamesByIdTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/GamesById.json"));
        Game var = mapper.readValue(node.get("data").toString(), Game.class);

        Assertions.assertEquals("w6jve26j", var.getId());
        Assertions.assertEquals("darksouls", var.getAbbreviation());
        Assertions.assertEquals(ModeratorRoles.SUPER_MODERATOR, var.getModerators().getModeratorRoles().get("kjpq14xq"));
        Assertions.assertTrue(var.getRuleset().isRequireVideo());
    }

    @Test
    void deserializeGamesById_EmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/GamesById_Embed.json"));
        Game var = mapper.readValue(node.get("data").toString(), Game.class);

        Assertions.assertEquals("w6jve26j", var.getId());
        Assertions.assertEquals("darksouls", var.getAbbreviation());
        Assertions.assertEquals("kjpq14xq", var.getModerators().getModeratorRolesEmbed().get(0).getId());
        Assertions.assertTrue(var.getRuleset().isRequireVideo());
        Assertions.assertEquals("mx6pwe3g", var.getPlatforms().getEmbedded().get(0).getId());
    }

    @Test
    void deserializeGamesByIdCategoriesTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/GamesByIdCategories.json"));
        List<Category> var = mapper.readValue(node.get("data").toString(), new TypeReference<List<Category>>() {});

        Assertions.assertEquals("9d86vww2", var.get(0).getId());
        Assertions.assertEquals(CategoryTypes.PER_GAME, var.get(0).getType());
        Assertions.assertEquals(1, var.get(0).getPlayers().getValue());
        Assertions.assertNull(var.get(0).getGame());
        Assertions.assertNull(var.get(0).getVariables());
    }

    @Test
    void deserializeGamesByIdCategories_EmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/GamesByIdCategories_Embed.json"));
        List<Category> var = mapper.readValue(node.get("data").toString(), new TypeReference<List<Category>>() {});

        Assertions.assertEquals("9d86vww2", var.get(0).getId());
        Assertions.assertEquals(CategoryTypes.PER_GAME, var.get(0).getType());
        Assertions.assertEquals(1, var.get(0).getPlayers().getValue());
        Assertions.assertNotNull(var.get(0).getGame());
        Assertions.assertNotNull(var.get(0).getVariables());
    }

    @Test
    void deserializeGamesByIdDerivedGamesTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/GamesByIdDerived_Games.json"));
        PagedResponse<Game> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Game>>() {});

        Assertions.assertEquals("lde33q56", var.getResourceList().get(0).getId());
        Assertions.assertEquals("100 Rooms of Enemies", var.getResourceList().get(0).getNames().getInternational());
        Assertions.assertTrue(var.getResourceList().get(0).getGametypes().getIds().contains("v4m291qw"));
    }

    @Test
    void deserializeGamesByIdDerivedGames_EmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/GamesByIdDerived_Games_Embed.json"));
        PagedResponse<Game> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Game>>() {});

        Assertions.assertEquals("lde33q56", var.getResourceList().get(0).getId());
        Assertions.assertEquals("100 Rooms of Enemies", var.getResourceList().get(0).getNames().getInternational());
        Assertions.assertEquals("v4m291qw", var.getResourceList().get(0).getGametypes().getEmbedded().get(0).getId());
    }

    @Test
    void deserializeGamesByIdLevelsTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/GamesByIdLevels.json"));
        List<Level> var = mapper.readValue(node.get("data").toString(), new TypeReference<List<Level>>() {});

        Assertions.assertEquals("5d74ypvd", var.get(0).getId());
        Assertions.assertNull(var.get(0).getRules());
        Assertions.assertNull(var.get(0).getCategories());
        Assertions.assertNull(var.get(0).getVariables());
    }

    @Test
    void deserializeGamesByIdLevels_EmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/GamesByIdLevels_Embed.json"));
        List<Level> var = mapper.readValue(node.get("data").toString(), new TypeReference<List<Level>>() {});

        Assertions.assertEquals("5d74ypvd", var.get(0).getId());
        Assertions.assertNull(var.get(0).getRules());
        Assertions.assertNotNull(var.get(0).getCategories());
        Assertions.assertNotNull(var.get(0).getVariables());
    }

    @Test
    void deserializeGamesByIdRecordsTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/GamesByIdRecords.json"));
        PagedResponse<Leaderboard> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Leaderboard>>() {});

        Assertions.assertEquals("pd0wq31e", var.getResourceList().get(0).getGame().getId());
        Assertions.assertEquals("7kjrn323", var.getResourceList().get(0).getCategory().getId());
        Assertions.assertEquals(1, var.getResourceList().get(0).getRuns().get(0).getPlace());
        Assertions.assertEquals("y25eqn6y", var.getResourceList().get(0).getRuns().get(0).getRun().getId());
    }

    @Test
    void deserializeGamesByIdRecords_EmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/GamesByIdRecords_Embed.json"));
        PagedResponse<Leaderboard> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Leaderboard>>() {});

        Assertions.assertEquals("pd0wq31e", var.getResourceList().get(0).getGame().getGameEmbed().getId());
        Assertions.assertEquals("7kjrn323", var.getResourceList().get(0).getCategory().getCategoryEmbed().getId());
        Assertions.assertEquals(1, var.getResourceList().get(0).getRuns().get(0).getPlace());
        Assertions.assertEquals("y25eqn6y", var.getResourceList().get(0).getRuns().get(0).getRun().getId());
    }

    @Test
    void deserializeGamesByIdVariablesTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/GamesByIdVariables.json"));
        List<Variable> var = mapper.readValue(node.get("data").toString(), new TypeReference<List<Variable>>() {});

        Assertions.assertEquals("0nwyx58q", var.get(0).getId());
        Assertions.assertEquals("NEW VERSION", var.get(0).getValues().getValues().get("xqke4ykq").getLabel());
        Assertions.assertFalse(var.get(0).getValues().getValues().get("xqke4ykq").getFlags().isMiscellaneous());
    }

}
