package com.fuzzylimes.jsr.clients.integration_tests;

import com.fuzzylimes.jsr.clients.GameClient;
import com.fuzzylimes.jsr.query_parameters.CategoryRecordsQuery;
import com.fuzzylimes.jsr.query_parameters.GamesQuery;
import com.fuzzylimes.jsr.query_parameters.LeaderboardQuery;
import com.fuzzylimes.jsr.query_parameters.sorting.*;
import com.fuzzylimes.jsr.resources.*;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

@Disabled
class GameClientIT {

    @Test
    void getGamesIT() throws IOException, UnexpectedResponseException {
        PagedResponse<Game> var = GameClient.getGames();
        getGamesResponseValidation(var);
    }

    @Test
    void getGamesQueryOrder_EmbedIT() throws IOException, UnexpectedResponseException {
        GamesQuery query = GamesQuery.builder().name("darksouls").build();
        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Game> var = GameClient.getGames(query, order, true);
        getGamesEmbedResponseValidation(var);
    }

    @Test
    void getGamesForSeriesBulkIT() throws IOException, UnexpectedResponseException {
        PagedResponse<BulkGame> var = GameClient.getGamesBulk();
        getGamesBulkResponseValidation(var);
    }

    @Test
    void getGamesForSeriesBulkQueryOrderIT() throws IOException, UnexpectedResponseException {
        GamesQuery query = GamesQuery.builder().name("darksouls").build();
        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<BulkGame> var = GameClient.getGamesBulk(query, order);
        Assertions.assertEquals(5, var.getPagination().getSize());
        Assertions.assertEquals("y65lw01e", var.getResourceList().get(0).getId());
        Assertions.assertEquals("Dark Souls II: Scholar of the First Sin", var.getResourceList().get(0).getNames().getInternational());
    }

    @Test
    void getGamesById_EmbedIT() throws IOException, UnexpectedResponseException {
        Game var = GameClient.getGamesById("w6jve26j", true);
        Assertions.assertEquals("w6jve26j", var.getId());
        Assertions.assertEquals("darksouls", var.getAbbreviation());
        Assertions.assertEquals("kjpq14xq", var.getModerators().getModeratorRolesEmbed().get(0).getId());
        Assertions.assertTrue(var.getRuleset().isRequireVideo());
        Assertions.assertEquals("mx6pwe3g", var.getPlatforms().getEmbedded().get(0).getId());
    }

    @Test
    void getCategoriesForGameIT() throws IOException, UnexpectedResponseException {
        List<Category> var = GameClient.getCategoriesForGame("w6jve26j");
        getGamesCategoriesResponseValidation(var);
    }

    @Test
    void getCategoriesForGameQueryOrder_EmbedIT() throws IOException, UnexpectedResponseException {
        CategoryRecordsQuery query = CategoryRecordsQuery.builder().skipEmpty(false).build();
        Sorting<CategoriesOrderBy> order = Sorting.<CategoriesOrderBy>builder().direction(Direction.ASCCENDING).build();
        List<Category> var = GameClient.getCategoriesForGame("w6jve26j", query, order, true);
        getGamesCategoriesEmbedResponseValidation(var);
    }

    @Test
    void getLevelsForGameIT() throws IOException, UnexpectedResponseException {
        List<Level> var = GameClient.getLevelsForGame("pdv0x91w");
        getGamesLevelsResponseValidation(var);
    }

    @Test
    void getLevelsForGameOrder_EmbedIT() throws IOException, UnexpectedResponseException {
        Sorting<LevelsOrderBy> order = Sorting.<LevelsOrderBy>builder().direction(Direction.ASCCENDING).build();
        List<Level> var = GameClient.getLevelsForGame("pdv0x91w", order, true);
        getGamesLevelsEmbedResponseValidation(var);
    }

    @Test
    void getVariablesForGameOrderIT() throws IOException, UnexpectedResponseException {
        Sorting<VariablesOrderBy> order = Sorting.<VariablesOrderBy>builder().direction(Direction.ASCCENDING).build();
        List<Variable> var = GameClient.getVariablesForGame("pdv0x91w", order);
        Assertions.assertEquals("0nwyx58q", var.get(0).getId());
        Assertions.assertEquals("NEW VERSION", var.get(0).getValues().getValues().get("xqke4ykq").getLabel());
        Assertions.assertFalse(var.get(0).getValues().getValues().get("xqke4ykq").getFlags().isMiscellaneous());
    }

    @Test
    void getDerivedGamesIT() throws IOException, UnexpectedResponseException {
        PagedResponse<Game> var = GameClient.getDerivedGames("pd0wq31e");
        getDerivedGamesResponseValidation(var);
    }

    @Test
    void getDerivedGamesQueryOrder_EmbedIT() throws IOException, UnexpectedResponseException {
        GamesQuery query = GamesQuery.builder().name("100 Rooms of Enemies").build();
        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Game> var = GameClient.getDerivedGames("pd0wq31e", query, order, true);
        getDerivedGamesEmbedResponseValidation(var);
    }

    @Test
    void getGameRecordsIT() throws IOException, UnexpectedResponseException {
        PagedResponse<Leaderboard> var = GameClient.getGameRecords("pd0wq31e");
        getGameRecordsResponseValidation(var);
    }

    @Test
    void getGameRecordsQuery_EmbedIT() throws IOException, UnexpectedResponseException {
        LeaderboardQuery query = LeaderboardQuery.builder().emulators(false).build();
        PagedResponse<Leaderboard> var = GameClient.getGameRecords("pd0wq31e", query, true);
        getGameRecordsEmbedResponseValidation(var);
    }

    private void getGamesResponseValidation(PagedResponse<Game> var) {
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("y65r071e", var.getResourceList().get(19).getId());
        Assertions.assertEquals("2015-01-03T22:44:26Z", var.getResourceList().get(19).getCreated());
        Assertions.assertTrue(var.getResourceList().get(19).getRegions().getIds().contains("e6lxy1dz"));
    }

    private void getGamesEmbedResponseValidation(PagedResponse<Game> var) {
        Assertions.assertEquals(5, var.getPagination().getSize());
        Assertions.assertEquals("y65lw01e", var.getResourceList().get(0).getId());
        Assertions.assertEquals("2015-03-27T22:09:05Z", var.getResourceList().get(0).getCreated());
        Assertions.assertEquals("o316x197", var.getResourceList().get(0).getRegions().getEmbeddedRegions().get(2).getId());
    }

    private void getGamesBulkResponseValidation(PagedResponse<BulkGame> var) {
        Assertions.assertEquals(250, var.getPagination().getSize());
        Assertions.assertEquals("y6544k0d", var.getResourceList().get(249).getId());
        Assertions.assertEquals("A Rose in the Twilight", var.getResourceList().get(249).getNames().getInternational());
    }

    private void getGamesCategoriesResponseValidation(List<Category> var) {
        Assertions.assertEquals("9d86vww2", var.get(0).getId());
        Assertions.assertEquals(CategoryTypes.PER_GAME, var.get(0).getType());
        Assertions.assertEquals(1, var.get(0).getPlayers().getValue());
        Assertions.assertNull(var.get(0).getGame());
        Assertions.assertNull(var.get(0).getVariables());
    }

    private void getGamesCategoriesEmbedResponseValidation(List<Category> var) {
        Assertions.assertEquals("9d86vww2", var.get(0).getId());
        Assertions.assertEquals(CategoryTypes.PER_GAME, var.get(0).getType());
        Assertions.assertEquals(1, var.get(0).getPlayers().getValue());
        Assertions.assertNotNull(var.get(0).getGame());
        Assertions.assertNotNull(var.get(0).getVariables());
    }

    private void getGamesLevelsResponseValidation(List<Level> var) {
        Assertions.assertEquals("5d74ypvd", var.get(0).getId());
        Assertions.assertNull(var.get(0).getRules());
        Assertions.assertNull(var.get(0).getCategories());
        Assertions.assertNull(var.get(0).getVariables());
    }

    private void getGamesLevelsEmbedResponseValidation(List<Level> var) {
        Assertions.assertEquals("5d74ypvd", var.get(0).getId());
        Assertions.assertNull(var.get(0).getRules());
        Assertions.assertNotNull(var.get(0).getCategories());
        Assertions.assertNotNull(var.get(0).getVariables());
    }

    private void getDerivedGamesResponseValidation(PagedResponse<Game> var) {
        Assertions.assertEquals("lde33q56", var.getResourceList().get(0).getId());
        Assertions.assertEquals("100 Rooms of Enemies", var.getResourceList().get(0).getNames().getInternational());
        Assertions.assertTrue(var.getResourceList().get(0).getGametypes().getIds().contains("v4m291qw"));
    }

    private void getDerivedGamesEmbedResponseValidation(PagedResponse<Game> var) {
        Assertions.assertEquals("lde33q56", var.getResourceList().get(0).getId());
        Assertions.assertEquals("100 Rooms of Enemies", var.getResourceList().get(0).getNames().getInternational());
        Assertions.assertEquals("v4m291qw", var.getResourceList().get(0).getGametypes().getEmbedded().get(0).getId());
    }

    private void getGameRecordsResponseValidation(PagedResponse<Leaderboard> var) {
        Assertions.assertEquals("pd0wq31e", var.getResourceList().get(0).getGame().getId());
        Assertions.assertEquals("7kjrn323", var.getResourceList().get(0).getCategory().getId());
        Assertions.assertEquals(1, var.getResourceList().get(0).getRuns().get(0).getPlace());
        Assertions.assertEquals("y25eqn6y", var.getResourceList().get(0).getRuns().get(0).getRun().getId());
    }

    private void getGameRecordsEmbedResponseValidation(PagedResponse<Leaderboard> var) {
        Assertions.assertEquals("pd0wq31e", var.getResourceList().get(0).getGame().getGameEmbed().getId());
        Assertions.assertEquals("7kjrn323", var.getResourceList().get(0).getCategory().getCategoryEmbed().getId());
        Assertions.assertEquals(1, var.getResourceList().get(0).getRuns().get(0).getPlace());
        Assertions.assertEquals("y25eqn6y", var.getResourceList().get(0).getRuns().get(0).getRun().getId());
    }
}