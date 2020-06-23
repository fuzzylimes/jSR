package com.fuzzylimes.jsr.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fuzzylimes.jsr.resources.EmbeddedGameTypes;
import com.fuzzylimes.jsr.resources.GameType;

import java.io.IOException;
import java.util.List;

public class EmbeddedGameTypesDeserializer extends JsonDeserializer<EmbeddedGameTypes> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public EmbeddedGameTypes deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);
        return buildCategory(root);
    }

    private EmbeddedGameTypes buildCategory(JsonNode root) throws JsonProcessingException {
        EmbeddedGameTypes gameTypes = new EmbeddedGameTypes();

        if (!root.has("data")) {
            CollectionType listOfGameTypes = mapper.getTypeFactory().constructCollectionType(List.class, String.class);
            List<String> ids = mapper.readValue(root.toString(), listOfGameTypes);
            gameTypes.setIds(ids);
        } else {
            CollectionType listOfGameTypes = mapper.getTypeFactory().constructCollectionType(List.class, GameType.class);
            List<GameType> val = mapper.readValue(root.get("data").toString(), listOfGameTypes);
            gameTypes.setEmbedded(val);
        }

        return gameTypes;
    }
}
