package com.fuzzylimes.jsr.JacksonTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.resources.Genre;
import com.fuzzylimes.jsr.resources.PagedResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GenresTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void deserializeGenresTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Genres.json"));
        PagedResponse<Genre> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Genre>>() {});

        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("d5ogj82w", var.getResourceList().get(3).getId());
    }

    @Test
    void deserializeGenresByIdTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/GenresById.json"));
        Genre var = mapper.readValue(node.get("data").toString(), Genre.class);

        Assertions.assertEquals(2, var.getLinks().size());
        Assertions.assertEquals("r2lwz857", var.getId());
    }
}
