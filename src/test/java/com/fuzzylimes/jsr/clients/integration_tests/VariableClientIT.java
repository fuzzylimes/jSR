package com.fuzzylimes.jsr.clients.integration_tests;

import com.fuzzylimes.jsr.clients.VariableClient;
import com.fuzzylimes.jsr.resources.Variable;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.fuzzylimes.jsr.resources.ScopeTypes.SINGLE_LEVEL;

@Disabled
class VariableClientIT {

    @Test
    void getVariableByIdIT() throws IOException, UnexpectedResponseException {
        Variable var = VariableClient.getVariableById("dlomqkj8");
        Assertions.assertEquals(9, var.getValues().getValues().size());
        Assertions.assertEquals(SINGLE_LEVEL, var.getScope().getType());
    }
}