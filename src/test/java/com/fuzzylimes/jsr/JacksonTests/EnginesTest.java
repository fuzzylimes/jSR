package com.fuzzylimes.jsr.JacksonTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.resources.Engine;
import com.fuzzylimes.jsr.resources.PagedResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class EnginesTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void deserializeEnginesTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Engines.json"));
        PagedResponse<Engine> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Engine>>() {});

        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("p39mo81j", var.getResourceList().get(3).getId());
    }

    @Test
    void deserializeEnginesByIdTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/EnginesById.json"));
        Engine var = mapper.readValue(node.get("data").toString(), Engine.class);

        Assertions.assertEquals(2, var.getLinks().size());
        Assertions.assertEquals("w3vgmn3o", var.getId());
    }
}
