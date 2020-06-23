package com.fuzzylimes.jsr.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fuzzylimes.jsr.resources.EmbeddedPlatforms;
import com.fuzzylimes.jsr.resources.Platform;

import java.io.IOException;
import java.util.List;

public class EmbeddedPlatformsDeserializer extends JsonDeserializer<EmbeddedPlatforms> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public EmbeddedPlatforms deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);
        return buildCategory(root);
    }

    private EmbeddedPlatforms buildCategory(JsonNode root) throws JsonProcessingException {
        EmbeddedPlatforms platforms = new EmbeddedPlatforms();

        if (!root.has("data")) {
            CollectionType listOfPlatforms = mapper.getTypeFactory().constructCollectionType(List.class, String.class);
            List<String> ids = mapper.readValue(root.toString(), listOfPlatforms);
            platforms.setIds(ids);
        } else {
            CollectionType listOfPlatforms = mapper.getTypeFactory().constructCollectionType(List.class, Platform.class);
            List<Platform> val = mapper.readValue(root.get("data").toString(), listOfPlatforms);
            platforms.setEmbedded(val);
        }

        return platforms;
    }
}
