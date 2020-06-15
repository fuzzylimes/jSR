package com.fuzzylimes.jsr.clients;

import com.fuzzylimes.jsr.resources.Variable;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrl;
import static com.fuzzylimes.jsr.resources.ScopeTypes.SINGLE_LEVEL;

class VariableClientTest {

    @Test
    void getVariableByIdTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrl("/responses/VariablesById.json");

        Variable var = VariableClient.getVariableById("asdf");
        Assertions.assertEquals(9, var.getValues().getValues().size());
        Assertions.assertEquals(SINGLE_LEVEL, var.getScope().getType());
    }

}