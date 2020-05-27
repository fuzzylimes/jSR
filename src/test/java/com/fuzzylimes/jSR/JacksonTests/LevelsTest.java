package com.fuzzylimes.jSR.JacksonTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jSR.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jSR.resources.Level;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.fuzzylimes.jSR.resources.CategoryTypes.PER_LEVEL;

public class LevelsTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserializeLevelsByIdTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/LevelsById.json"));
        Level var = mapper.readValue(node.get("data").toString(), Level.class);
        System.out.println(node);

        Assertions.assertEquals("5d74ypvd", var.getId());
        Assertions.assertEquals(7, var.getLinks().size());
    }

    @Test
    public void deserializeLevelsByIdEmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/LevelsById_Embed.json"));
        Level var = mapper.readValue(node.get("data").toString(), Level.class);
        System.out.println(node);

        Assertions.assertEquals("5d74ypvd", var.getId());
        Assertions.assertEquals(7, var.getLinks().size());
        Assertions.assertEquals(2, var.getCategories().size());
        Assertions.assertEquals(PER_LEVEL, var.getCategories().get(1).getType());
        Assertions.assertEquals(1, var.getVariables().size());
        Assertions.assertEquals(false, var.getVariables().get(0).isSubcategory());
    }
}
