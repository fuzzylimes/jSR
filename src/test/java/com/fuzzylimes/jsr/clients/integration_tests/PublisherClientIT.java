package com.fuzzylimes.jsr.clients.integration_tests;

import com.fuzzylimes.jsr.clients.PublishersClient;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.PublishersOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Publisher;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Disabled
public class PublisherClientIT {
    @Test
    void getPublishers_SortingTest() throws IOException, UnexpectedResponseException {
        Sorting<PublishersOrderBy> order = Sorting.<PublishersOrderBy>builder()
                .direction(Direction.ASCCENDING).orderBy(PublishersOrderBy.NAME).build();
        PagedResponse<Publisher> var = PublishersClient.getPublishers(order);
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("363dg4nk", var.getResourceList().get(3).getId());
    }

    @Test
    void getPublishersByIdTest() throws IOException, UnexpectedResponseException {
        Publisher var = PublishersClient.getPublishersById("w4npp2nl");
        Assertions.assertEquals(2, var.getLinks().size());
        Assertions.assertEquals("w4npp2nl", var.getId());
    }
}
