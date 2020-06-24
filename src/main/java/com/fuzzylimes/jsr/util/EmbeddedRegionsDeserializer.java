package com.fuzzylimes.jsr.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fuzzylimes.jsr.resources.EmbeddedRegions;
import com.fuzzylimes.jsr.resources.Region;

import java.io.IOException;
import java.util.List;

/**
 * Custom Jackson JsonDeserializer to handle embedded Region objects when deserializing response payloads.
 */
public class EmbeddedRegionsDeserializer extends JsonDeserializer<EmbeddedRegions> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public EmbeddedRegions deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);

        EmbeddedRegions embeddedRegions = new EmbeddedRegions();

        if (!root.has("data")) {
            CollectionType listOfRegions = mapper.getTypeFactory().constructCollectionType(List.class, String.class);
            List<String> ids = mapper.readValue(root.toString(), listOfRegions);
            embeddedRegions.setIds(ids);
        } else {
            CollectionType listOfRegions = mapper.getTypeFactory().constructCollectionType(List.class, Region.class);
            List<Region> val = mapper.readValue(root.get("data").toString(), listOfRegions);
            embeddedRegions.setEmbedded(val);
        }

        return embeddedRegions;
    }
}
