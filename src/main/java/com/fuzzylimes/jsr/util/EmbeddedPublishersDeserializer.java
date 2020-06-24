package com.fuzzylimes.jsr.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fuzzylimes.jsr.resources.EmbeddedPublishers;
import com.fuzzylimes.jsr.resources.Publisher;

import java.io.IOException;
import java.util.List;

/**
 * Custom Jackson JsonDeserializer to handle embedded Publisher objects when deserializing response payloads.
 */
public class EmbeddedPublishersDeserializer extends JsonDeserializer<EmbeddedPublishers> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public EmbeddedPublishers deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);

        EmbeddedPublishers developers = new EmbeddedPublishers();

        if (!root.has("data")) {
            CollectionType listOfPublishers = mapper.getTypeFactory().constructCollectionType(List.class, String.class);
            List<String> ids = mapper.readValue(root.toString(), listOfPublishers);
            developers.setIds(ids);
        } else {
            CollectionType listOfPublishers = mapper.getTypeFactory().constructCollectionType(List.class, Publisher.class);
            List<Publisher> val = mapper.readValue(root.get("data").toString(), listOfPublishers);
            developers.setEmbedded(val);
        }

        return developers;
    }
}
