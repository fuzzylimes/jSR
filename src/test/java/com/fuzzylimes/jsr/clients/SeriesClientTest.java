package com.fuzzylimes.jsr.clients;

import com.fuzzylimes.jsr.query_parameters.GamesQuery;
import com.fuzzylimes.jsr.query_parameters.SeriesQuery;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.GamesOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.SeriesOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.*;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrl;
import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrlAndQueryParams;
import static com.fuzzylimes.jsr.resources.ModeratorRoles.SUPER_MODERATOR;

class SeriesClientTest {

    @Test
    void getSeriesTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Series.json");

        PagedResponse<Series> var = SeriesClient.getSeries();
        getSeriesResponseValidation(var);
    }

    @Test
    void getSeriesByQueryTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Series.json");

        SeriesQuery query = SeriesQuery.builder().name(".hack").build();
        PagedResponse<Series> var = SeriesClient.getSeries(query);
        getSeriesResponseValidation(var);
    }

    @Test
    void getSeriesBySortingTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Series.json");

        Sorting<SeriesOrderBy> order = Sorting.<SeriesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Series> var = SeriesClient.getSeries(order);
        getSeriesResponseValidation(var);
    }

    @Test
    void getSeriesBySortingQueryTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Series.json");

        SeriesQuery query = SeriesQuery.builder().name(".hack").build();
        Sorting<SeriesOrderBy> order = Sorting.<SeriesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Series> var = SeriesClient.getSeries(query, order);
        getSeriesResponseValidation(var);
    }

    @Test
    void getSeries_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Series_Embed.json");

        PagedResponse<Series> var = SeriesClient.getSeries();
        getSeriesEmbedResponseValidation(var);
    }

    @Test
    void getSeriesByQuery_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Series_Embed.json");

        SeriesQuery query = SeriesQuery.builder().name(".hack").build();
        PagedResponse<Series> var = SeriesClient.getSeries(query, true);
        getSeriesEmbedResponseValidation(var);
    }

    @Test
    void getSeriesBySorting_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Series_Embed.json");

        Sorting<SeriesOrderBy> order = Sorting.<SeriesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Series> var = SeriesClient.getSeries(order, true);
        getSeriesEmbedResponseValidation(var);
    }

    @Test
    void getSeriesBySortingQuery_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Series_Embed.json");

        SeriesQuery query = SeriesQuery.builder().name(".hack").build();
        Sorting<SeriesOrderBy> order = Sorting.<SeriesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Series> var = SeriesClient.getSeries(query, order, true);
        getSeriesEmbedResponseValidation(var);
    }

    @Test
    void getSeriesByIdTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrl("/responses/SeriesById.json");

        Series var = SeriesClient.getSeriesById("8nwjpj7y");
        Assertions.assertEquals(".hack", var.getAbbreviation());
        Assertions.assertEquals(SUPER_MODERATOR, var.getModerators().getModeratorRoles().get("48ge6kyj"));
    }

    @Test
    void getSeriesById_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/SeriesById_Embed.json");

        Series var = SeriesClient.getSeriesById("8nwjpj7y", true);
        Assertions.assertEquals(".hack", var.getAbbreviation());
        Assertions.assertEquals("68we7q8g", var.getModerators().getModeratorRolesEmbed().get(0).getId());
    }

    @Test
    void getGamesForSeriesTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/SeriesByIdGames.json");

        PagedResponse<Game> var = SeriesClient.getGamesForSeries("8nwjpj7y");
        getGamesForSeriesValidate(var);
    }

    @Test
    void getGamesForSeriesQueryTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/SeriesByIdGames.json");

        GamesQuery query = GamesQuery.builder().name(".hack").build();
        PagedResponse<Game> var = SeriesClient.getGamesForSeries("8nwjpj7y", query);
        getGamesForSeriesValidate(var);
    }

    @Test
    void getGamesForSeriesOrderTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/SeriesByIdGames.json");

        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Game> var = SeriesClient.getGamesForSeries("8nwjpj7y", order);
        getGamesForSeriesValidate(var);
    }

    @Test
    void getGamesForSeriesQueryOrderTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/SeriesByIdGames.json");

        GamesQuery query = GamesQuery.builder().name(".hack").build();
        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Game> var = SeriesClient.getGamesForSeries("8nwjpj7y", query, order);
        getGamesForSeriesValidate(var);
    }

    @Test
    void getGamesForSeries_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/SeriesByIdGames_Embed.json");

        PagedResponse<Game> var = SeriesClient.getGamesForSeries("8nwjpj7y", true);
        getGamesForSeriesEmbedValidate(var);
    }

    @Test
    void getGamesForSeriesQuery_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/SeriesByIdGames_Embed.json");

        GamesQuery query = GamesQuery.builder().name(".hack").build();
        PagedResponse<Game> var = SeriesClient.getGamesForSeries("8nwjpj7y", query, true);
        getGamesForSeriesEmbedValidate(var);
    }

    @Test
    void getGamesForSeriesOrder_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/SeriesByIdGames_Embed.json");

        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Game> var = SeriesClient.getGamesForSeries("8nwjpj7y", order, true);
        getGamesForSeriesEmbedValidate(var);
    }

    @Test
    void getGamesForSeriesQueryOrder_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/SeriesByIdGames_Embed.json");

        GamesQuery query = GamesQuery.builder().name(".hack").build();
        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Game> var = SeriesClient.getGamesForSeries("8nwjpj7y", query, order, true);
        getGamesForSeriesEmbedValidate(var);
    }

    @Test
    void getGamesForSeriesBulkTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/SeriesByIdGames_Bulk.json");

        PagedResponse<BulkGame> var = SeriesClient.getGamesForSeriesBulk("8nwjpj7y");
        getGamesForSeriesBulkValidation(var);
    }

    @Test
    void getGamesForSeriesBulkOrderTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/SeriesByIdGames_Bulk.json");

        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<BulkGame> var = SeriesClient.getGamesForSeriesBulk("8nwjpj7y", order);
        getGamesForSeriesBulkValidation(var);
    }

    @Test
    void getGamesForSeriesBulkQueryTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/SeriesByIdGames_Bulk.json");

        GamesQuery query = GamesQuery.builder().name(".hack").build();
        PagedResponse<BulkGame> var = SeriesClient.getGamesForSeriesBulk("8nwjpj7y", query);
        getGamesForSeriesBulkValidation(var);
    }

    @Test
    void getGamesForSeriesBulkQueryOrderTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/SeriesByIdGames_Bulk.json");

        GamesQuery query = GamesQuery.builder().name(".hack").build();
        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<BulkGame> var = SeriesClient.getGamesForSeriesBulk("8nwjpj7y", query, order);
        getGamesForSeriesBulkValidation(var);
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
        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals(".hack", var.getResourceList().get(0).getAbbreviation());
        Assertions.assertEquals("68we7q8g", var.getResourceList().get(0).getModerators().getModeratorRolesEmbed().get(0).getId());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

    private void getGamesForSeriesValidate(PagedResponse<Game> var) {
        Assertions.assertEquals("guhd", var.getResourceList().get(0).getAbbreviation());
        Assertions.assertEquals(SUPER_MODERATOR, var.getResourceList().get(0).getModerators().getModeratorRoles().get("qjoee786"));
        Assertions.assertTrue(var.getResourceList().get(0).getPlatforms().getIds().contains("nzelkr6q"));
        Assertions.assertTrue(var.getResourceList().get(0).getDevelopers().getIds().contains("1zkl9pej"));
    }

    private void getGamesForSeriesEmbedValidate(PagedResponse<Game> var) {
        Assertions.assertEquals("guhd", var.getResourceList().get(0).getAbbreviation());
        Assertions.assertEquals(UserRoles.USER, var.getResourceList().get(0).getModerators().getModeratorRolesEmbed().get(0).getRole());
        Assertions.assertEquals("nzelkr6q", var.getResourceList().get(0).getPlatforms().getEmbedded().get(0).getId());
        Assertions.assertEquals("1zkl9pej", var.getResourceList().get(0).getDevelopers().getEmbedded().get(0).getId());
    }
}