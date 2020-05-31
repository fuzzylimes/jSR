package com.fuzzylimes.jSR.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fuzzylimes.jSR.resources.Category;
import com.fuzzylimes.jSR.resources.EmbeddedCategory;

import java.io.IOException;
import java.util.List;

public class EmbeddedCategoryDeserializer extends JsonDeserializer {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public EmbeddedCategory deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);
        return buildCategory(root);
    }

    private EmbeddedCategory buildCategory(JsonNode root) throws JsonProcessingException {
        EmbeddedCategory embeddedCategory = new EmbeddedCategory();

        if (!root.has("data")) {
            String idString = mapper.readValue(root.toString(), String.class);
            embeddedCategory.setId(idString);
        } else {
            Category val = mapper.readValue(root.get("data").toString(), Category.class);
            embeddedCategory.setCategoryEmbed(val);
        }

        return embeddedCategory;
    }
}
