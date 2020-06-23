package com.fuzzylimes.jsr.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.resources.EmbeddedGame;
import com.fuzzylimes.jsr.resources.Game;

import java.io.IOException;

public class EmbeddedGameDeserializer extends JsonDeserializer<EmbeddedGame> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public EmbeddedGame deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);
        return buildGames(root);
    }

    private EmbeddedGame buildGames(JsonNode root) throws JsonProcessingException {
        EmbeddedGame embeddedGame = new EmbeddedGame();

        if (!root.has("data")) {
            String idString = mapper.readValue(root.toString(), String.class);
            embeddedGame.setId(idString);
        } else {
            Game val = mapper.readValue(root.get("data").toString(), Game.class);
            embeddedGame.setGameEmbed(val);
        }

        return embeddedGame;
    }
}
