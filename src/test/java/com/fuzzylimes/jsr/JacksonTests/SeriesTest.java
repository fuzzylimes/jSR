package com.fuzzylimes.jsr.JacksonTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.resources.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.fuzzylimes.jsr.resources.ModeratorRoles.SUPER_MODERATOR;

public class SeriesTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserializeSeriesTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Series.json"));
        PagedResponse<Series> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Series>>() {});

        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals(".hack", var.getResourceList().get(0).getAbbreviation());
        Assertions.assertEquals(SUPER_MODERATOR, var.getResourceList().get(0).getModerators().getModeratorRoles().get("48ge6kyj"));
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

    @Test
    public void deserializeSeriesEmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Series_Embed.json"));
        PagedResponse<Series> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Series>>() {});

        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals(".hack", var.getResourceList().get(0).getAbbreviation());
        Assertions.assertEquals("68we7q8g", var.getResourceList().get(0).getModerators().getModeratorRolesEmbed().get(0).getId());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

    @Test
    public void deserializeSeriesByIdTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/SeriesById.json"));
        Series var = mapper.readValue(node.get("data").toString(), Series.class);

        Assertions.assertEquals(".hack", var.getAbbreviation());
        Assertions.assertEquals(SUPER_MODERATOR, var.getModerators().getModeratorRoles().get("48ge6kyj"));
    }

    @Test
    public void deserializeSeriesById_EmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/SeriesById_Embed.json"));
        Series var = mapper.readValue(node.get("data").toString(), Series.class);

        Assertions.assertEquals(".hack", var.getAbbreviation());
        Assertions.assertEquals("68we7q8g", var.getModerators().getModeratorRolesEmbed().get(0).getId());
    }

    @Test
    public void deserializeSeriesByIdGamesTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/SeriesByIdGames.json"));
        PagedResponse<Game> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Game>>() {});

        Assertions.assertEquals("guhd", var.getResourceList().get(0).getAbbreviation());
        Assertions.assertEquals(SUPER_MODERATOR, var.getResourceList().get(0).getModerators().getModeratorRoles().get("qjoee786"));
        Assertions.assertTrue(var.getResourceList().get(0).getPlatforms().getIds().contains("nzelkr6q"));
        Assertions.assertTrue(var.getResourceList().get(0).getDevelopers().getIds().contains("1zkl9pej"));
    }

    @Test
    public void deserializeSeriesByIdGames_EmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/SeriesByIdGames_Embed.json"));
        PagedResponse<Game> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Game>>() {});

        Assertions.assertEquals("guhd", var.getResourceList().get(0).getAbbreviation());
        Assertions.assertEquals(UserRoles.USER, var.getResourceList().get(0).getModerators().getModeratorRolesEmbed().get(0).getRole());
        Assertions.assertEquals("nzelkr6q", var.getResourceList().get(0).getPlatforms().getEmbedded().get(0).getId());
        Assertions.assertEquals("1zkl9pej", var.getResourceList().get(0).getDevelopers().getEmbedded().get(0).getId());
    }

    @Test
    public void deserializeSeriesByIdGamesBulkTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/SeriesByIdGames_Bulk.json"));
        PagedResponse<BulkGame> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<BulkGame>>() {});

        Assertions.assertEquals("guhd", var.getResourceList().get(0).getAbbreviation());
        Assertions.assertEquals(".hack//G.U. Last Recode", var.getResourceList().get(0).getNames().getInternational());
        Assertions.assertNull(var.getResourceList().get(0).getNames().getJapanese());
    }
}
