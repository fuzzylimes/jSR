package com.fuzzylimes.jsr.clients.integration_tests;

import com.fuzzylimes.jsr.clients.EnginesClient;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.EnginesOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.Engine;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class EnginesClientIT {
    @Test
    @Disabled
    void getEngines_SortingTest() throws IOException, UnexpectedResponseException {
        Sorting<EnginesOrderBy> order = Sorting.<EnginesOrderBy>builder()
                .direction(Direction.ASCCENDING).orderBy(EnginesOrderBy.NAME).build();
        PagedResponse<Engine> var = EnginesClient.getEngines(order);
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("p39mo81j", var.getResourceList().get(3).getId());
    }

    @Test
    @Disabled
    void getEnginesByIdTest() throws IOException, UnexpectedResponseException {
        Engine var = EnginesClient.getEnginesById("w3vgmn3o");
        Assertions.assertEquals(2, var.getLinks().size());
        Assertions.assertEquals("w3vgmn3o", var.getId());
    }
}
