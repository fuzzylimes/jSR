package com.fuzzylimes.jsr.clients.integration_tests;

import com.fuzzylimes.jsr.clients.SeriesClient;
import com.fuzzylimes.jsr.query_parameters.GamesQuery;
import com.fuzzylimes.jsr.query_parameters.SeriesQuery;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.GamesOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.SeriesOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.*;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.fuzzylimes.jsr.resources.ModeratorRoles.SUPER_MODERATOR;

@Disabled
class SeriesClientIT {

    @Test
    void getSeriesTest() throws IOException, UnexpectedResponseException {
        PagedResponse<Series> var = SeriesClient.getSeries();
        getSeriesResponseValidation(var);
    }

    @Test
    void getSeriesBySortingQuery_EmbedTest() throws IOException, UnexpectedResponseException {
        SeriesQuery query = SeriesQuery.builder().name(".hack").build();
        Sorting<SeriesOrderBy> order = Sorting.<SeriesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Series> var = SeriesClient.getSeries(query, order, true);
        getSeriesEmbedResponseValidation(var);
    }

    @Test
    void getSeriesById_EmbedTest() throws IOException, UnexpectedResponseException {
        Series var = SeriesClient.getSeriesById("8nwjpj7y", true);
        Assertions.assertEquals(".hack", var.getAbbreviation());
        Assertions.assertEquals("68we7q8g", var.getModerators().getModeratorRolesEmbed().get(0).getId());
    }

    @Test
    void getGamesForSeriesTest() throws IOException, UnexpectedResponseException {
        PagedResponse<Game> var = SeriesClient.getGamesForSeries("8nwjpj7y");
        getGamesForSeriesValidate(var);
    }

    @Test
    void getGamesForSeriesQueryOrder_EmbedTest() throws IOException, UnexpectedResponseException {
        GamesQuery query = GamesQuery.builder().name(".hack").build();
        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Game> var = SeriesClient.getGamesForSeries("8nwjpj7y", query, order, true);
        getGamesForSeriesEmbedValidate(var);
    }

    @Test
    void getGamesForSeriesBulkTest() throws IOException, UnexpectedResponseException {
        PagedResponse<BulkGame> var = SeriesClient.getGamesForSeriesBulk("8nwjpj7y");
        getGamesForSeriesBulkValidation(var);
    }

    @Test
    void getGamesForSeriesBulkQueryOrderTest() throws IOException, UnexpectedResponseException {
        GamesQuery query = GamesQuery.builder().name(".hack").build();
        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<BulkGame> var = SeriesClient.getGamesForSeriesBulk("8nwjpj7y", query, order);
        Assertions.assertEquals(".hack_g.u._volume_3_redemption", var.getResourceList().get(0).getAbbreviation());
        Assertions.assertEquals(".hack//G.U. Volume 3: Redemption", var.getResourceList().get(0).getNames().getInternational());
        Assertions.assertNull(var.getResourceList().get(0).getNames().getJapanese());
    }

    private void getGamesForSeriesBulkValidation(PagedResponse<BulkGame> var) {
        Assertions.assertEquals("guhd", var.getResourceList().get(0).getAbbreviation());
        Assertions.assertEquals(".hack//G.U. Last Recode", var.getResourceList().get(0).getNames().getInternational());
        Assertions.assertNull(var.getResourceList().get(0).getNames().getJapanese());
    }

    private void getSeriesResponseValidation(PagedResponse<Series> var) {
        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals(".hack", var.getResourceList().get(0).getAbbreviation());
        Assertions.assertEquals(SUPER_MODERATOR, var.getResourceList().get(0).getModerators().getModeratorRoles().get("48ge6kyj"));
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

    private void getSeriesEmbedResponseValidation(PagedResponse<Series> var) {
        Assertions.assertEquals(1, var.getResourceList().size());
        Assertions.assertEquals(1, var.getPagination().getSize());
        Assertions.assertEquals(".hack", var.getResourceList().get(0).getAbbreviation());
        Assertions.assertEquals("8nwjpj7y", var.getResourceList().get(0).getModerators().getModeratorRolesEmbed().get(0).getId());
    }

    private void getGamesForSeriesValidate(PagedResponse<Game> var) {
        Assertions.assertEquals("guhd", var.getResourceList().get(0).getAbbreviation());
        Assertions.assertEquals(SUPER_MODERATOR, var.getResourceList().get(0).getModerators().getModeratorRoles().get("qjoee786"));
        Assertions.assertTrue(var.getResourceList().get(0).getPlatforms().getIds().contains("nzelkr6q"));
        Assertions.assertTrue(var.getResourceList().get(0).getDevelopers().getIds().contains("1zkl9pej"));
    }

    private void getGamesForSeriesEmbedValidate(PagedResponse<Game> var) {
        Assertions.assertEquals(".hack_g.u._volume_3_redemption", var.getResourceList().get(0).getAbbreviation());
        Assertions.assertEquals(UserRoles.USER, var.getResourceList().get(0).getModerators().getModeratorRolesEmbed().get(0).getRole());
        Assertions.assertEquals("n5e17e27", var.getResourceList().get(0).getPlatforms().getEmbedded().get(0).getId());
        Assertions.assertEquals("1zkl9pej", var.getResourceList().get(0).getDevelopers().getEmbedded().get(0).getId());
    }
}