package com.fuzzylimes.jSR.util;//package com.fuzzylimes.jSR.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fuzzylimes.jSR.resources.Category;
import com.fuzzylimes.jSR.resources.Variable;

import java.io.IOException;
import java.util.List;

public class VariableEmbedDeserializer extends JsonDeserializer {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Variable> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);
        return buildVariable(root);
    }

    private List<Variable> buildVariable(JsonNode root) throws JsonProcessingException {
        CollectionType listOfVariables = mapper.getTypeFactory().constructCollectionType(List.class, Variable.class);
        return mapper.readValue(root.get("data").toString(), listOfVariables);
    }
}
