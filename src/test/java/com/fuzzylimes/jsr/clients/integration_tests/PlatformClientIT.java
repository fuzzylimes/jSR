package com.fuzzylimes.jsr.clients.integration_tests;

import com.fuzzylimes.jsr.clients.PlatformClient;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.PlatformsOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Platform;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class PlatformClientIT {

    @Test
    @Disabled
    void getPlatforms_SortingIT() throws IOException, UnexpectedResponseException {
        Sorting<PlatformsOrderBy> order = Sorting.<PlatformsOrderBy>builder().direction(Direction.ASCCENDING).orderBy(PlatformsOrderBy.NAME).build();
        PagedResponse<Platform> var = PlatformClient.getPlatforms(order);
        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

}