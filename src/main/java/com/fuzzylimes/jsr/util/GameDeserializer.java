package com.fuzzylimes.jsr.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.resources.Game;

import java.io.IOException;

/**
 * Custom Jackson JsonDeserializer to handle embedded Game objects when deserializing response payloads.
 */
public class GameDeserializer extends JsonDeserializer<Game> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public Game deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);

        return mapper.readValue(root.get("data").toString(), Game.class);
    }
}
