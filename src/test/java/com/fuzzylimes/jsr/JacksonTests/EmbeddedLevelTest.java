package com.fuzzylimes.jsr.JacksonTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.resources.Category;
import com.fuzzylimes.jsr.resources.Level;
import com.fuzzylimes.jsr.resources.Variable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.fuzzylimes.jsr.resources.CategoryTypes.PER_LEVEL;

public class EmbeddedLevelTest {
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

    @Test
    public void deserializeLevelsByIdCategoriesTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/LevelsByIdCategories.json"));
        List<Category> var = mapper.readValue(node.get("data").toString(), new TypeReference<List<Category>>() {});
        System.out.println(node);

        Assertions.assertEquals(2, var.size());
        Assertions.assertEquals("7dgrergk", var.get(0).getId());
        Assertions.assertEquals(PER_LEVEL, var.get(0).getType());
    }

    @Test
    public void deserializeLevelsByIdVariablesTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/LevelsByIdVariables.json"));
        List<Variable> var = mapper.readValue(node.get("data").toString(), new TypeReference<List<Variable>>() {});
        System.out.println(node);

        Assertions.assertEquals(1, var.size());
        Assertions.assertEquals("0nw2mjdn", var.get(0).getId());
        Assertions.assertNull(var.get(0).getValues().getDefaultValue());
        Assertions.assertEquals("Luigi", var.get(0).getValues().getValues().get("0q5oeer1").getLabel());
    }

}
