package com.fuzzylimes.jsr.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.resources.Region;

import java.io.IOException;

/**
 * Custom Jackson JsonDeserializer to handle an optional embedded Region object when deserializing response payloads.
 */
public class EmbeddedRegionDeserializer extends JsonDeserializer<Region> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public Region deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);

        if (root.get("data").isArray()) {
            return new Region();
        } else {
            return mapper.readValue(root.get("data").toString(), Region.class);
        }
    }
}
