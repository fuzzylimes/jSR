package com.fuzzylimes.jsr.JacksonTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Region;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RegionsTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserializeRegionsTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Regions.json"));
        PagedResponse<Region> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Region>>() {});

        Assertions.assertEquals(6, var.getResourceList().size());
        Assertions.assertEquals(6, var.getPagination().getSize());
        Assertions.assertEquals("pr184lqn", var.getResourceList().get(5).getId());
    }

    @Test
    public void deserializeRegionsByIdTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/RegionsById.json"));
        Region var = mapper.readValue(node.get("data").toString(), Region.class);

        Assertions.assertEquals("mol4z19n", var.getId());
        Assertions.assertEquals(3, var.getLinks().size());
    }
}
