package com.fuzzylimes.jsr.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.resources.Level;
import com.fuzzylimes.jsr.resources.EmbeddedLevel;

import java.io.IOException;

/**
 * Custom Jackson JsonDeserializer to handle embedded Level objects when deserializing response payloads.
 */
public class EmbeddedLevelDeserializer extends JsonDeserializer<EmbeddedLevel> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public EmbeddedLevel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);

        EmbeddedLevel embeddedLevel = new EmbeddedLevel();

        if (!root.has("data")) {
            String idString = mapper.readValue(root.toString(), String.class);
            embeddedLevel.setId(idString);
        } else {
            if (root.get("data").isArray()) {
                return embeddedLevel;
            }
            Level val = mapper.readValue(root.get("data").toString(), Level.class);
            embeddedLevel.setLevelEmbed(val);
        }

        return embeddedLevel;
    }
}
