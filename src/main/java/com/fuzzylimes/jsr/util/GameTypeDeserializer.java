//package com.fuzzylimes.jsr.util;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.ObjectCodec;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.type.CollectionType;
//import com.fuzzylimes.jsr.model.GameType;
//import com.fuzzylimes.jsr.model.GameTypes;
//
//import java.io.IOException;
//import java.util.List;
//
//public class GameTypeDeserializer extends JsonDeserializer {
//
//    ObjectMapper mapper = new ObjectMapper();
//
//    @Override
//    public GameTypes deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//        ObjectCodec oc = jsonParser.getCodec();
//        JsonNode root = oc.readTree(jsonParser);
//        return buildGameTypes(root);
//    }
//
//    private GameTypes buildGameTypes(JsonNode root) throws JsonProcessingException {
//        GameTypes gameTypes = new GameTypes();
//
//        if (root.isArray()) {
//            CollectionType listOfStringType = mapper.getTypeFactory().constructCollectionType(List.class, String.class);
//            List<String> val = mapper.readValue(root.toString(), listOfStringType);
//            gameTypes.setIds(val);
//        } else {
//            CollectionType listOfGameTypes = mapper.getTypeFactory().constructCollectionType(List.class, GameType.class);
//            List<GameType> val = mapper.readValue(root.toString(), listOfGameTypes);
//            gameTypes.setGameTypeList(val);
//        }
//
//        return gameTypes;
//    }
//}
