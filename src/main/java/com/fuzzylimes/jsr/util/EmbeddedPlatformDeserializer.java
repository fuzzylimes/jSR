package com.fuzzylimes.jsr.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.resources.Platform;

import java.io.IOException;

/**
 * Custom Jackson JsonDeserializer to handle embedded Platform objects when deserializing response payloads.
 */
public class EmbeddedPlatformDeserializer extends JsonDeserializer<Platform> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public Platform deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);

        if (root.get("data").isArray()) {
            return new Platform();
        } else {
            return mapper.readValue(root.get("data").toString(), Platform.class);
        }
    }
}
