package com.fuzzylimes.jsr.JacksonTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.resources.Variable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.fuzzylimes.jsr.resources.ScopeTypes.SINGLE_LEVEL;

public class VariablesTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserializeVariablesTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/VariablesById.json"));
        Variable var = mapper.readValue(node.get("data").toString(), Variable.class);

        Assertions.assertEquals(9, var.getValues().getValues().size());
        Assertions.assertEquals(SINGLE_LEVEL, var.getScope().getType());
    }
}
