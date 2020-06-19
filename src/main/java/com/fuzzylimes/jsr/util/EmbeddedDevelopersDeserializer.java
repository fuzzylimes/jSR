package com.fuzzylimes.jsr.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fuzzylimes.jsr.resources.Developer;
import com.fuzzylimes.jsr.resources.EmbeddedDevelopers;

import java.io.IOException;
import java.util.List;

public class EmbeddedDevelopersDeserializer extends JsonDeserializer<EmbeddedDevelopers> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public EmbeddedDevelopers deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);
        return buildCategory(root);
    }

    private EmbeddedDevelopers buildCategory(JsonNode root) throws JsonProcessingException {
        EmbeddedDevelopers developers = new EmbeddedDevelopers();

        if (!root.has("data")) {
            CollectionType listOfDevelopers = mapper.getTypeFactory().constructCollectionType(List.class, String.class);
            List<String> ids = mapper.readValue(root.toString(), listOfDevelopers);
            developers.setIds(ids);
        } else {
            CollectionType listOfDevelopers = mapper.getTypeFactory().constructCollectionType(List.class, Developer.class);
            List<Developer> val = mapper.readValue(root.get("data").toString(), listOfDevelopers);
            developers.setEmbedded(val);
        }

        return developers;
    }
}
