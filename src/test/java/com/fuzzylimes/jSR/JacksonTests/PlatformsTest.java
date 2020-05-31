package com.fuzzylimes.jSR.JacksonTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jSR.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jSR.resources.PagedResponse;
import com.fuzzylimes.jSR.resources.Platform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PlatformsTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserializePlatformsTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Platforms.json"));
        PagedResponse<Platform> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Platform>>() {});
        System.out.println(node);

        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

    @Test
    public void deserializePlatformByIdTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/PlatformsById.json"));
        Platform var = mapper.readValue(node.get("data").toString(), Platform.class);
        System.out.println(node);

        Assertions.assertEquals("jm9577eo", var.getId());
        Assertions.assertEquals(3, var.getLinks().size());
    }
}
