package com.fuzzylimes.jSR.util;//package com.fuzzylimes.jSR.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fuzzylimes.jSR.resources.Category;
import com.fuzzylimes.jSR.resources.ModeratorRoles;
import com.fuzzylimes.jSR.resources.Moderators;
import com.fuzzylimes.jSR.resources.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmbeddedCategoriesDeserializer extends JsonDeserializer {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Category> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);
        return buildCategory(root);
    }

    private List<Category> buildCategory(JsonNode root) throws JsonProcessingException {
        CollectionType listOfCategories = mapper.getTypeFactory().constructCollectionType(List.class, Category.class);
        return mapper.readValue(root.get("data").toString(), listOfCategories);
    }
}
