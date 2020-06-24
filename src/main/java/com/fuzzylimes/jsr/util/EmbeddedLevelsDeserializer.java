package com.fuzzylimes.jsr.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fuzzylimes.jsr.resources.Level;

import java.io.IOException;
import java.util.List;

/**
 * Custom Jackson JsonDeserializer to handle a list of embedded Level objects when deserializing response payloads.
 */
public class EmbeddedLevelsDeserializer extends JsonDeserializer<List<Level>> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Level> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);

        CollectionType listOfLevels = mapper.getTypeFactory().constructCollectionType(List.class, Level.class);
        return mapper.readValue(root.get("data").toString(), listOfLevels);
    }
}
