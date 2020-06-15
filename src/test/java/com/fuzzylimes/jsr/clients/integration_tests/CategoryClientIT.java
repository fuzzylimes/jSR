package com.fuzzylimes.jsr.clients.integration_tests;

import com.fuzzylimes.jsr.clients.CategoryClient;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.query_parameters.sorting.VariablesOrderBy;
import com.fuzzylimes.jsr.resources.Leaderboard;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.ScopeTypes;
import com.fuzzylimes.jsr.resources.Variable;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class CategoryClientIT {

    @Test
    @Disabled
    void getVariablesForCategoryIT() {

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

    @Test
    @Disabled
    void getCategoriesByIdRecordsIT() throws IOException, UnexpectedResponseException {
        PagedResponse<Leaderboard> var = CategoryClient.getCategoryRecords("jdzme6kv");
        Assertions.assertEquals("pdv0x91w", var.getResourceList().get(0).getGame().getId());
        Assertions.assertEquals(1, var.getResourceList().get(0).getRuns().get(0).getPlace());
        Assertions.assertEquals("yjkv04gm", var.getResourceList().get(0).getRuns().get(0).getRun().getId());
        Assertions.assertEquals("0jm6w6o8", var.getResourceList().get(0).getRuns().get(0).getRun().getStatus().getExaminer());
    }
}