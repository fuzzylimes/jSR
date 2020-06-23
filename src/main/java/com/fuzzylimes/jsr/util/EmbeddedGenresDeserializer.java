package com.fuzzylimes.jsr.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fuzzylimes.jsr.resources.EmbeddedGenres;
import com.fuzzylimes.jsr.resources.Genre;

import java.io.IOException;
import java.util.List;

public class EmbeddedGenresDeserializer extends JsonDeserializer<EmbeddedGenres> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public EmbeddedGenres deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);
        return buildCategory(root);
    }

    private EmbeddedGenres buildCategory(JsonNode root) throws JsonProcessingException {
        EmbeddedGenres genres = new EmbeddedGenres();

        if (!root.has("data")) {
            CollectionType listOfGenres = mapper.getTypeFactory().constructCollectionType(List.class, String.class);
            List<String> ids = mapper.readValue(root.toString(), listOfGenres);
            genres.setIds(ids);
        } else {
            CollectionType listOfGenres = mapper.getTypeFactory().constructCollectionType(List.class, Genre.class);
            List<Genre> val = mapper.readValue(root.get("data").toString(), listOfGenres);
            genres.setEmbedded(val);
        }

        return genres;
    }
}
