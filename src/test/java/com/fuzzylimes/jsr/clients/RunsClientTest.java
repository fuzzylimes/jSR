package com.fuzzylimes.jsr.clients;

import com.fuzzylimes.jsr.query_parameters.RunsQuery;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.RunsOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Run;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrlAndQueryParams;

class RunsClientTest {

    @Test
    void getRuns_ParamsAndSortingEmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Runs_Embed.json");

        RunsQuery query = RunsQuery.builder().build();
        Sorting<RunsOrderBy> order = Sorting.<RunsOrderBy>builder().direction(Direction.ASCCENDING).orderBy(RunsOrderBy.GAME).build();

        PagedResponse<Run> var = RunsClient.getRuns(query, order, true);
        getRunsVerifyEmbed(var);
    }

    @Test
    void getRuns_ParamsAndSortingdTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Runs.json");

        RunsQuery query = RunsQuery.builder().build();
        Sorting<RunsOrderBy> order = Sorting.<RunsOrderBy>builder().direction(Direction.ASCCENDING).orderBy(RunsOrderBy.GAME).build();

        PagedResponse<Run> var = RunsClient.getRuns(query, order);
        getRunsVerify(var);
    }

    @Test
    void getRuns_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Runs_Embed.json");

        PagedResponse<Run> var = RunsClient.getRuns(true);
        getRunsVerifyEmbed(var);
    }

    @Test
    void getRuns_OrderTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Runs.json");

        Sorting<RunsOrderBy> order = Sorting.<RunsOrderBy>builder().direction(Direction.ASCCENDING).orderBy(RunsOrderBy.GAME).build();

        PagedResponse<Run> var = RunsClient.getRuns(order);
        getRunsVerify(var);
    }

    @Test
    void getRuns_QueryTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Runs.json");

        RunsQuery query = RunsQuery.builder().emulated(false).build();

        PagedResponse<Run> var = RunsClient.getRuns(query);
        getRunsVerify(var);
    }

    @Test
    void getRuns_QueryEmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Runs_Embed.json");

        RunsQuery query = RunsQuery.builder().emulated(false).build();

        PagedResponse<Run> var = RunsClient.getRuns(query, true);
        getRunsVerifyEmbed(var);
    }

    @Test
    void getRuns_OrderEmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Runs_Embed.json");

        Sorting<RunsOrderBy> order = Sorting.<RunsOrderBy>builder().direction(Direction.ASCCENDING).orderBy(RunsOrderBy.GAME).build();

        PagedResponse<Run> var = RunsClient.getRuns(order, true);
        getRunsVerifyEmbed(var);
    }

    @Test
    void getRunsTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Runs.json");

        PagedResponse<Run> var = RunsClient.getRuns();
        getRunsVerify(var);
    }

    private void getRunsVerifyEmbed(PagedResponse<Run> var) {
        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
        Assertions.assertEquals("opydqjmn", var.getResourceList().get(1).getId());
        Assertions.assertEquals("nj1ne1p4", var.getResourceList().get(1).getGame().getGameEmbed().getId());
        Assertions.assertEquals("kv813xp5", var.getResourceList().get(1).getPlayers().getPlayersEmbed().getUserList().get(0).getId());
    }

    private void getRunsVerify(PagedResponse<Run> var) {
        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
        Assertions.assertEquals("opydqjmn", var.getResourceList().get(1).getId());
        Assertions.assertEquals("prklq2n9", var.getResourceList().get(1).getCategory().getId());
        Assertions.assertEquals("w89rwelk", var.getResourceList().get(1).getSystem().getPlatform());
    }

    @Test
    void getRunByIdTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/RunsById.json");

        Run var = RunsClient.getRunById("1wzpqgyq");
        Assertions.assertEquals("1wzpqgyq", var.getId());
        Assertions.assertEquals("PT21M52S", var.getTimes().getPrimary());
        Assertions.assertEquals("w89rwelk", var.getSystem().getPlatform());
    }

    @Test
    void getRunById_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/RunsById_Embed.json");

        Run var = RunsClient.getRunById("1wzpqgyq", true);
        Assertions.assertEquals("1wzpqgyq", var.getId());
        Assertions.assertEquals("PT21M52S", var.getTimes().getPrimary());
        Assertions.assertEquals("w89rwelk", var.getSystem().getPlatform());
        Assertions.assertEquals("w89rwelk", var.getGame().getGameEmbed().getPlatforms().getIds().get(0));
        Assertions.assertEquals("prklq2n9", var.getCategory().getCategoryEmbed().getId());
    }

}