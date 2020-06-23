package com.fuzzylimes.jsr.JacksonTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.resources.GameType;
import com.fuzzylimes.jsr.resources.PagedResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GameTypesTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void deserializeGameTypesTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/GameTypes.json"));
        PagedResponse<GameType> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<GameType>>() {});

        Assertions.assertEquals(12, var.getPagination().getSize());
        Assertions.assertEquals("4xm721op", var.getResourceList().get(10).getId());
    }

    @Test
    void deserializeGameTypesByIdTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/GameTypesById.json"));
        GameType var = mapper.readValue(node.get("data").toString(), GameType.class);

        Assertions.assertEquals(2, var.getLinks().size());
        Assertions.assertEquals("lyn97m9o", var.getId());
    }
}
