package com.fuzzylimes.jsr.JacksonTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.resources.Publisher;
import com.fuzzylimes.jsr.resources.PagedResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PublishersTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void deserializePublishersTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Publishers.json"));
        PagedResponse<Publisher> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Publisher>>() {});

        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("363dg4nk", var.getResourceList().get(3).getId());
    }

    @Test
    void deserializePublishersByIdTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/PublishersById.json"));
        Publisher var = mapper.readValue(node.get("data").toString(), Publisher.class);

        Assertions.assertEquals(2, var.getLinks().size());
        Assertions.assertEquals("w4npp2nl", var.getId());
    }
}
