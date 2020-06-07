package com.fuzzylimes.jsr.clients;

import com.fuzzylimes.jsr.resources.ScopeTypes;
import com.fuzzylimes.jsr.resources.Variable;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.query_parameters.sorting.VariablesOrderBy;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class CategoryClientTest {

    @Test
    void getVariablesForCategory() {

        Sorting<VariablesOrderBy> sorting = Sorting.<VariablesOrderBy>builder()
                .direction(Direction.DESCENDING)
                .orderBy(VariablesOrderBy.NAME)
                .build();

        try {
            List<Variable> response = CategoryClient.getVariablesForCategory("jdzme6kv", sorting);
            Assertions.assertEquals(ScopeTypes.FULL_GAME, response.get(1).getScope().getType());
            Assertions.assertEquals("0nw2mjdn", response.get(0).getId());
        } catch (IOException | UnexpectedResponseException e) {
            e.printStackTrace();
        }
    }
}