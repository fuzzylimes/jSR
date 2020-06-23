package com.fuzzylimes.jsr.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fuzzylimes.jsr.resources.Engine;
import com.fuzzylimes.jsr.resources.EmbeddedEngines;

import java.io.IOException;
import java.util.List;

public class EmbeddedEnginesDeserializer extends JsonDeserializer<EmbeddedEngines> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public EmbeddedEngines deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);
        return buildCategory(root);
    }

    private EmbeddedEngines buildCategory(JsonNode root) throws JsonProcessingException {
        EmbeddedEngines engines = new EmbeddedEngines();

        if (!root.has("data")) {
            CollectionType listOfEngines = mapper.getTypeFactory().constructCollectionType(List.class, String.class);
            List<String> ids = mapper.readValue(root.toString(), listOfEngines);
            engines.setIds(ids);
        } else {
            CollectionType listOfEngines = mapper.getTypeFactory().constructCollectionType(List.class, Engine.class);
            List<Engine> val = mapper.readValue(root.get("data").toString(), listOfEngines);
            engines.setEmbedded(val);
        }

        return engines;
    }
}
