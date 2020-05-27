//package com.fuzzylimes.jSR.util;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.ObjectCodec;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.type.CollectionType;
//import com.fuzzylimes.jSR.model.GameType;
//import com.fuzzylimes.jSR.model.GameTypes;
//import com.fuzzylimes.jSR.model.Platform;
//import com.fuzzylimes.jSR.model.Platforms;
//
//import java.io.IOException;
//import java.util.List;
//
//public class PlatformDeserializer extends JsonDeserializer {
//
//    ObjectMapper mapper = new ObjectMapper();
//
//    @Override
//    public Platforms deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//        ObjectCodec oc = jsonParser.getCodec();
//        JsonNode root = oc.readTree(jsonParser);
//        return buildPlatforms(root);
//    }
//
//    private Platforms buildPlatforms(JsonNode root) throws JsonProcessingException {
//        Platforms platforms = new Platforms();
//
//        if (root.isArray()) {
//            CollectionType listOfStringType = mapper.getTypeFactory().constructCollectionType(List.class, String.class);
//            List<String> val = mapper.readValue(root.toString(), listOfStringType);
//            platforms.setIds(val);
//        } else {
//            CollectionType listOfPlatforms = mapper.getTypeFactory().constructCollectionType(List.class, Platform.class);
//            List<Platform> val = mapper.readValue(root.toString(), listOfPlatforms);
//            platforms.setPlatformList(val);
//        }
//
//        return platforms;
//    }
//}
