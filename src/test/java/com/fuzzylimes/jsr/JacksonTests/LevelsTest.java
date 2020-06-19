package com.fuzzylimes.jsr.JacksonTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.resources.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.System;
import java.util.List;

import static com.fuzzylimes.jsr.resources.CategoryTypes.PER_LEVEL;

public class LevelsTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserializeLevelsByIdTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/LevelsById.json"));
        Level var = mapper.readValue(node.get("data").toString(), Level.class);

        Assertions.assertEquals("5d74ypvd", var.getId());
        Assertions.assertEquals(7, var.getLinks().size());
    }

    @Test
    public void deserializeLevelsByIdEmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/LevelsById_Embed.json"));
        Level var = mapper.readValue(node.get("data").toString(), Level.class);

        Assertions.assertEquals("5d74ypvd", var.getId());
        Assertions.assertEquals(7, var.getLinks().size());
        Assertions.assertEquals(2, var.getCategories().size());
        Assertions.assertEquals(PER_LEVEL, var.getCategories().get(1).getType());
        Assertions.assertEquals(1, var.getVariables().size());
        Assertions.assertFalse(var.getVariables().get(0).isSubcategory());
    }

    @Test
    public void deserializeLevelsByIdCategoriesTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/LevelsByIdCategories.json"));
        List<Category> var = mapper.readValue(node.get("data").toString(), new TypeReference<List<Category>>() {});

        Assertions.assertEquals(2, var.size());
        Assertions.assertEquals("7dgrergk", var.get(0).getId());
        Assertions.assertEquals(PER_LEVEL, var.get(0).getType());
    }

    @Test
    public void deserializeLevelsByIdCategories_EmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/LevelsByIdCategories_Embed.json"));
        List<Category> var = mapper.readValue(node.get("data").toString(), new TypeReference<List<Category>>() {});

        Assertions.assertEquals(2, var.size());
        Assertions.assertEquals("7dgrergk", var.get(0).getId());
        Assertions.assertEquals(PER_LEVEL, var.get(0).getType());
        Assertions.assertEquals("pdv0x91w", var.get(0).getGame().getId());
        Assertions.assertEquals("0nw2mjdn", var.get(0).getVariables().get(0).getId());
    }

    @Test
    public void deserializeLevelsByIdVariablesTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/LevelsByIdVariables.json"));
        List<Variable> var = mapper.readValue(node.get("data").toString(), new TypeReference<List<Variable>>() {});

        Assertions.assertEquals(1, var.size());
        Assertions.assertEquals("0nw2mjdn", var.get(0).getId());
        Assertions.assertNull(var.get(0).getValues().getDefaultValue());
        Assertions.assertEquals("Luigi", var.get(0).getValues().getValues().get("0q5oeer1").getLabel());
    }

    @Test
    public void deserializeLevelsByIdRecordsTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/LevelsByIdRecords.json"));
        PagedResponse<Leaderboard> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Leaderboard>>() {});

        Assertions.assertEquals(2, var.getResourceList().size());
        Assertions.assertEquals("5d74ypvd", var.getResourceList().get(0).getLevel().getId());
        Assertions.assertNull(var.getResourceList().get(1).getPlatform());
        Assertions.assertEquals("v8lnen2x", var.getResourceList().get(1).getRuns().get(0).getRun().getPlayers().getPlayers().get(0).getId());
    }

    @Test
    public void deserializeLevelsByIdRecords_EmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/LevelsByIdRecords_Embed.json"));
        PagedResponse<Leaderboard> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Leaderboard>>() {});

        Assertions.assertEquals(2, var.getResourceList().size());
        Assertions.assertEquals("5d74ypvd", var.getResourceList().get(0).getLevel().getLevelEmbed().getId());
        Assertions.assertNull(var.getResourceList().get(1).getPlatform());
        Assertions.assertEquals("v8lnen2x", var.getResourceList().get(1).getRuns().get(0).getRun().getPlayers().getPlayers().get(0).getId());
    }

}
