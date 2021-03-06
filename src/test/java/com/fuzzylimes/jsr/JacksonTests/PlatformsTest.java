package com.fuzzylimes.jsr.JacksonTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Platform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PlatformsTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserializePlatformsTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Platforms.json"));
        PagedResponse<Platform> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Platform>>() {});

        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

    @Test
    public void deserializePlatformByIdTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/PlatformsById.json"));
        Platform var = mapper.readValue(node.get("data").toString(), Platform.class);

        Assertions.assertEquals("jm9577eo", var.getId());
        Assertions.assertEquals(3, var.getLinks().size());
    }
}
