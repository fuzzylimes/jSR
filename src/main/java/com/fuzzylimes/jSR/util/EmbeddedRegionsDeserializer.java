package com.fuzzylimes.jSR.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fuzzylimes.jSR.resources.EmbeddedGameRegion;
import com.fuzzylimes.jSR.resources.GameRegion;
import com.fuzzylimes.jSR.resources.Region;

import java.io.IOException;
import java.util.List;

public class EmbeddedRegionsDeserializer extends JsonDeserializer {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public GameRegion deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);
        return buildCategory(root);
    }

    private GameRegion buildCategory(JsonNode root) throws JsonProcessingException {
        GameRegion gameRegion = new GameRegion();

        if (!root.has("data")) {
            CollectionType listOfRegions = mapper.getTypeFactory().constructCollectionType(List.class, String.class);
            List<String> ids = mapper.readValue(root.toString(), listOfRegions);
            gameRegion.setIds(ids);
        } else {
            CollectionType listOfRegions = mapper.getTypeFactory().constructCollectionType(List.class, EmbeddedGameRegion.class);
            List<EmbeddedGameRegion> val = mapper.readValue(root.get("data").toString(), listOfRegions);
            gameRegion.setEmbeddedRegions(val);
        }

        return gameRegion;
    }
}
