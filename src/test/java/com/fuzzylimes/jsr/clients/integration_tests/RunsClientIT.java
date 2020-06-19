package com.fuzzylimes.jsr.clients.integration_tests;

import com.fuzzylimes.jsr.clients.RunsClient;
import com.fuzzylimes.jsr.query_parameters.RunsQuery;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.RunsOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Run;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrlAndQueryParams;

class RunsClientIT {

    @Test
    @Disabled
    void getRuns_ParamsAndSortingTest() throws IOException, UnexpectedResponseException {
        RunsQuery query = RunsQuery.builder().build();
        Sorting<RunsOrderBy> order = Sorting.<RunsOrderBy>builder().direction(Direction.ASCCENDING).orderBy(RunsOrderBy.GAME).build();

        PagedResponse<Run> var = RunsClient.getRuns(query, order, true);
        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
        Assertions.assertEquals("opydqjmn", var.getResourceList().get(1).getId());
        Assertions.assertEquals("nj1ne1p4", var.getResourceList().get(1).getGame().getGameEmbed().getId());
        Assertions.assertEquals("kv813xp5", var.getResourceList().get(1).getPlayers().getPlayersEmbed().getUserList().get(0).getId());
    }

    @Test
    @Disabled
    void getRunById_EmbedTest() throws IOException, UnexpectedResponseException {
        Run var = RunsClient.getRunById("1wzpqgyq", true);
        Assertions.assertEquals("1wzpqgyq", var.getId());
        Assertions.assertEquals("PT21M52S", var.getTimes().getPrimary());
        Assertions.assertEquals("w89rwelk", var.getSystem().getPlatform());
        Assertions.assertEquals("w89rwelk", var.getGame().getGameEmbed().getPlatforms().get(0));
        Assertions.assertEquals("prklq2n9", var.getCategory().getCategoryEmbed().getId());
    }

}