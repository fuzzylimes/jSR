package com.fuzzylimes.jsr.clients.integration_tests;

import com.fuzzylimes.jsr.clients.RegionClient;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.RegionsOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Region;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Disabled
class RegionClientIT {

    @Test
    void getRegions_SortingTest() throws IOException, UnexpectedResponseException {
        Sorting<RegionsOrderBy> order = Sorting.<RegionsOrderBy>builder().direction(Direction.ASCCENDING).orderBy(RegionsOrderBy.NAME).build();
        PagedResponse<Region> var = RegionClient.getRegions(order);
        Assertions.assertEquals(6, var.getResourceList().size());
        Assertions.assertEquals(6, var.getPagination().getSize());
        Assertions.assertEquals("pr184lqn", var.getResourceList().get(5).getId());
    }

    @Test
    void getRegionByIdTest() throws IOException, UnexpectedResponseException {
        Region var = RegionClient.getRegionById("mol4z19n");
        Assertions.assertEquals("mol4z19n", var.getId());
        Assertions.assertEquals(3, var.getLinks().size());
    }
}