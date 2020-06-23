package com.fuzzylimes.jsr.JacksonTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.resources.Developer;
import com.fuzzylimes.jsr.resources.PagedResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DevelopersTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void deserializeDevelopersTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Developers.json"));
        PagedResponse<Developer> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Developer>>() {});

        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("yzo7mleq", var.getResourceList().get(3).getId());
    }

    @Test
    void deserializeDevelopersByIdTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/DevelopersById.json"));
        Developer var = mapper.readValue(node.get("data").toString(), Developer.class);

        Assertions.assertEquals(2, var.getLinks().size());
        Assertions.assertEquals("5mznpr6r", var.getId());
    }
}
