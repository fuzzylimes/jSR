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

public class CategoriesTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserializeCategoriesTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/CategoriesById.json"));
        Category var = mapper.readValue(node.get("data").toString(), Category.class);

        Assertions.assertFalse(var.isMiscellaneous());
        Assertions.assertEquals("jdzme6kv", var.getId());
        Assertions.assertEquals(1, var.getPlayers().getValue());

    }

    @Test
    public void deserializeCategoriesEmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/CategoriesById_Embed.json"));
        Category var = mapper.readValue(node.get("data").toString(), Category.class);

        Assertions.assertFalse(var.isMiscellaneous());
        Assertions.assertEquals("jdzme6kv", var.getId());
        Assertions.assertEquals(1, var.getPlayers().getValue());
        Assertions.assertEquals("realtime", var.getGame().getRuleset().getDefaultTime());
        Assertions.assertTrue(var.getVariables().get(0).isMandatory());
        Assertions.assertTrue(var.getVariables().get(0).getValues().getValues().containsKey("zqomrrp1"));

    }

    @Test
    public void deserializeCategoriesByIdRecordsTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/CategoriesByIdRecords.json"));
        PagedResponse<Leaderboard> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Leaderboard>>() {});

        Assertions.assertEquals("pdv0x91w", var.getResourceList().get(0).getGame().getId());
        Assertions.assertEquals(1, var.getResourceList().get(0).getRuns().get(0).getPlace());
        Assertions.assertEquals("yjkv04gm", var.getResourceList().get(0).getRuns().get(0).getRun().getId());
        Assertions.assertEquals("0jm6w6o8", var.getResourceList().get(0).getRuns().get(0).getRun().getStatus().getExaminer());
    }

    @Test
    public void deserializeCategoriesByIdRecordsEmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/CategoriesByIdRecords_Embed.json"));
        PagedResponse<Leaderboard> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Leaderboard>>() {});

        Assertions.assertEquals("pdv0x91w", var.getResourceList().get(0).getGame().getGameEmbed().getId());
        Assertions.assertEquals(1, var.getResourceList().get(0).getRuns().get(0).getPlace());
        Assertions.assertEquals("yjkv04gm", var.getResourceList().get(0).getRuns().get(0).getRun().getId());
        Assertions.assertEquals("0jm6w6o8", var.getResourceList().get(0).getRuns().get(0).getRun().getStatus().getExaminer());
        Assertions.assertEquals("us", var.getResourceList().get(0).getPlayers().getPlayersEmbed().getUserList().get(0).getLocation().getCountry().getCode());

    }

    @Test
    public void deserializeCategoriesByIdVariablesTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/CategoriesByIdVariables.json"));
        List<Variable> var = mapper.readValue(node.get("data").toString(), new TypeReference<List<Variable>>() {});

        Assertions.assertEquals(ScopeTypes.GLOBAL, var.get(0).getScope().getType());
        Assertions.assertEquals("9l73ympn", var.get(1).getId());

    }
}
