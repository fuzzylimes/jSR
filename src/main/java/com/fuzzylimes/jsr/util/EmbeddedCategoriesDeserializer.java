package com.fuzzylimes.jsr.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fuzzylimes.jsr.resources.Category;

import java.io.IOException;
import java.util.List;

public class EmbeddedCategoriesDeserializer extends JsonDeserializer<List<Category>> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Category> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);
        return buildCategory(root);
    }

    private List<Category> buildCategory(JsonNode root) throws JsonProcessingException {
        CollectionType listOfCategories = mapper.getTypeFactory().constructCollectionType(List.class, Category.class);
        return mapper.readValue(root.get("data").toString(), listOfCategories);
    }
}
