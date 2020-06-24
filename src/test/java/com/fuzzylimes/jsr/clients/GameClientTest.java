package com.fuzzylimes.jsr.clients;

import com.fuzzylimes.jsr.query_parameters.CategoryRecordsQuery;
import com.fuzzylimes.jsr.query_parameters.GamesQuery;
import com.fuzzylimes.jsr.query_parameters.LeaderboardQuery;
import com.fuzzylimes.jsr.query_parameters.sorting.*;
import com.fuzzylimes.jsr.resources.*;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrl;
import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrlAndQueryParams;

class GameClientTest {

    @Test
    void getGamesTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Games.json");

        PagedResponse<Game> var = GameClient.getGames();
        getGamesResponseValidation(var);
    }

    @Test
    void getGamesQueryTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Games.json");

        GamesQuery query = GamesQuery.builder().name("darksouls").build();
        PagedResponse<Game> var = GameClient.getGames(query);
        getGamesResponseValidation(var);
    }

    @Test
    void getGamesOrderTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Games.json");

        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Game> var = GameClient.getGames(order);
        getGamesResponseValidation(var);
    }

    @Test
    void getGamesQueryOrderTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Games.json");

        GamesQuery query = GamesQuery.builder().name("darksouls").build();
        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Game> var = GameClient.getGames(query, order);
        getGamesResponseValidation(var);
    }

    @Test
    void getGames_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Games_Embed.json");

        PagedResponse<Game> var = GameClient.getGames(true);
        getGamesEmbedResponseValidation(var);
    }

    @Test
    void getGamesQuery_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Games_Embed.json");

        GamesQuery query = GamesQuery.builder().name("darksouls").build();
        PagedResponse<Game> var = GameClient.getGames(query, true);
        getGamesEmbedResponseValidation(var);
    }

    @Test
    void getGamesOrder_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Games_Embed.json");

        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Game> var = GameClient.getGames(order, true);
        getGamesEmbedResponseValidation(var);
    }

    @Test
    void getGamesQueryOrder_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Games_Embed.json");

        GamesQuery query = GamesQuery.builder().name("darksouls").build();
        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Game> var = GameClient.getGames(query, order, true);
        getGamesEmbedResponseValidation(var);
    }

    @Test
    void getGamesForSeriesBulkTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Games_Bulk.json");

        PagedResponse<BulkGame> var = GameClient.getGamesBulk();
        getGamesBulkResponseValidation(var);
    }

    @Test
    void getGamesForSeriesBulkQueryTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Games_Bulk.json");

        GamesQuery query = GamesQuery.builder().name("darksouls").build();
        PagedResponse<BulkGame> var = GameClient.getGamesBulk(query);
        getGamesBulkResponseValidation(var);
    }

    @Test
    void getGamesForSeriesBulkOrderTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Games_Bulk.json");

        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<BulkGame> var = GameClient.getGamesBulk(order);
        getGamesBulkResponseValidation(var);
    }

    @Test
    void getGamesForSeriesBulkQueryOrderTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Games_Bulk.json");

        GamesQuery query = GamesQuery.builder().name("darksouls").build();
        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<BulkGame> var = GameClient.getGamesBulk(query, order);
        getGamesBulkResponseValidation(var);
    }

    @Test
    void getGamesByIdTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrl("/responses/GamesById.json");

        Game var = GameClient.getGamesById("w6jve26j");
        Assertions.assertEquals("w6jve26j", var.getId());
        Assertions.assertEquals("darksouls", var.getAbbreviation());
        Assertions.assertEquals(ModeratorRoles.SUPER_MODERATOR, var.getModerators().getModeratorRoles().get("kjpq14xq"));
        Assertions.assertTrue(var.getRuleset().isRequireVideo());
    }

    @Test
    void getGamesById_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesById_Embed.json");

        Game var = GameClient.getGamesById("w6jve26j", true);
        Assertions.assertEquals("w6jve26j", var.getId());
        Assertions.assertEquals("darksouls", var.getAbbreviation());
        Assertions.assertEquals("kjpq14xq", var.getModerators().getModeratorRolesEmbed().get(0).getId());
        Assertions.assertTrue(var.getRuleset().isRequireVideo());
        Assertions.assertEquals("mx6pwe3g", var.getPlatforms().getEmbedded().get(0).getId());
    }

    @Test
    void getCategoriesForGameTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdCategories.json");

        List<Category> var = GameClient.getCategoriesForGame("w6jve26j");
        getGamesCategoriesResponseValidation(var);
    }

    @Test
    void getCategoriesForGameQueryTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdCategories.json");

        CategoryRecordsQuery query = CategoryRecordsQuery.builder().skipEmpty(false).build();
        List<Category> var = GameClient.getCategoriesForGame("w6jve26j", query);
        getGamesCategoriesResponseValidation(var);
    }

    @Test
    void getCategoriesForGameOrderTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdCategories.json");

        Sorting<CategoriesOrderBy> order = Sorting.<CategoriesOrderBy>builder().direction(Direction.ASCCENDING).build();
        List<Category> var = GameClient.getCategoriesForGame("w6jve26j", order);
        getGamesCategoriesResponseValidation(var);
    }

    @Test
    void getCategoriesForGameQueryOrderTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdCategories.json");

        CategoryRecordsQuery query = CategoryRecordsQuery.builder().skipEmpty(false).build();
        Sorting<CategoriesOrderBy> order = Sorting.<CategoriesOrderBy>builder().direction(Direction.ASCCENDING).build();
        List<Category> var = GameClient.getCategoriesForGame("w6jve26j", query, order);
        getGamesCategoriesResponseValidation(var);
    }

    @Test
    void getCategoriesForGame_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdCategories_Embed.json");

        List<Category> var = GameClient.getCategoriesForGame("w6jve26j", true);
        getGamesCategoriesEmbedResponseValidation(var);
    }

    @Test
    void getCategoriesForGameQuery_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdCategories_Embed.json");

        CategoryRecordsQuery query = CategoryRecordsQuery.builder().skipEmpty(false).build();
        List<Category> var = GameClient.getCategoriesForGame("w6jve26j", query, true);
        getGamesCategoriesEmbedResponseValidation(var);
    }

    @Test
    void getCategoriesForGameOrder_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdCategories_Embed.json");

        Sorting<CategoriesOrderBy> order = Sorting.<CategoriesOrderBy>builder().direction(Direction.ASCCENDING).build();
        List<Category> var = GameClient.getCategoriesForGame("w6jve26j", order, true);
        getGamesCategoriesEmbedResponseValidation(var);
    }

    @Test
    void getCategoriesForGameQueryOrder_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdCategories_Embed.json");

        CategoryRecordsQuery query = CategoryRecordsQuery.builder().skipEmpty(false).build();
        Sorting<CategoriesOrderBy> order = Sorting.<CategoriesOrderBy>builder().direction(Direction.ASCCENDING).build();
        List<Category> var = GameClient.getCategoriesForGame("w6jve26j", query, order, true);
        getGamesCategoriesEmbedResponseValidation(var);
    }

    @Test
    void getLevelsForGameTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdLevels.json");

        List<Level> var = GameClient.getLevelsForGame("pdv0x91w");
        getGamesLevelsResponseValidation(var);
    }

    @Test
    void getLevelsForGameOrderTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdLevels.json");

        Sorting<LevelsOrderBy> order = Sorting.<LevelsOrderBy>builder().direction(Direction.ASCCENDING).build();
        List<Level> var = GameClient.getLevelsForGame("pdv0x91w", order);
        getGamesLevelsResponseValidation(var);
    }

    @Test
    void getLevelsForGame_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdLevels_Embed.json");

        List<Level> var = GameClient.getLevelsForGame("pdv0x91w", true);
        getGamesLevelsEmbedResponseValidation(var);
    }

    @Test
    void getLevelsForGameOrder_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdLevels_Embed.json");

        Sorting<LevelsOrderBy> order = Sorting.<LevelsOrderBy>builder().direction(Direction.ASCCENDING).build();
        List<Level> var = GameClient.getLevelsForGame("pdv0x91w", order, true);
        getGamesLevelsEmbedResponseValidation(var);
    }

    @Test
    void getVariablesForGameTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdVariables.json");

        List<Variable> var = GameClient.getVariablesForGame("pdv0x91w");
        Assertions.assertEquals("0nwyx58q", var.get(0).getId());
        Assertions.assertEquals("NEW VERSION", var.get(0).getValues().getValues().get("xqke4ykq").getLabel());
        Assertions.assertFalse(var.get(0).getValues().getValues().get("xqke4ykq").getFlags().isMiscellaneous());
    }

    @Test
    void getVariablesForGameOrderTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdVariables.json");

        Sorting<VariablesOrderBy> order = Sorting.<VariablesOrderBy>builder().direction(Direction.ASCCENDING).build();
        List<Variable> var = GameClient.getVariablesForGame("pdv0x91w", order);
        Assertions.assertEquals("0nwyx58q", var.get(0).getId());
        Assertions.assertEquals("NEW VERSION", var.get(0).getValues().getValues().get("xqke4ykq").getLabel());
        Assertions.assertFalse(var.get(0).getValues().getValues().get("xqke4ykq").getFlags().isMiscellaneous());
    }

    @Test
    void getDerivedGamesTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdDerived_Games.json");

        PagedResponse<Game> var = GameClient.getDerivedGames("pd0wq31e");
        getDerivedGamesResponseValidation(var);
    }

    @Test
    void getDerivedGamesQueryTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdDerived_Games.json");

        GamesQuery query = GamesQuery.builder().name("darksouls").build();
        PagedResponse<Game> var = GameClient.getDerivedGames("pd0wq31e", query);
        getDerivedGamesResponseValidation(var);
    }

    @Test
    void getDerivedGamesOrderTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdDerived_Games.json");

        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Game> var = GameClient.getDerivedGames("pd0wq31e", order);
        getDerivedGamesResponseValidation(var);
    }

    @Test
    void getDerivedGamesQueryOrderTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdDerived_Games.json");

        GamesQuery query = GamesQuery.builder().name("darksouls").build();
        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Game> var = GameClient.getDerivedGames("pd0wq31e", query, order);
        getDerivedGamesResponseValidation(var);
    }

    @Test
    void getDerivedGames_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdDerived_Games_Embed.json");

        PagedResponse<Game> var = GameClient.getDerivedGames("pd0wq31e", true);
        getDerivedGamesEmbedResponseValidation(var);
    }

    @Test
    void getDerivedGamesQuery_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdDerived_Games_Embed.json");

        GamesQuery query = GamesQuery.builder().name("darksouls").build();
        PagedResponse<Game> var = GameClient.getDerivedGames("pd0wq31e", query, true);
        getDerivedGamesEmbedResponseValidation(var);
    }

    @Test
    void getDerivedGamesOrder_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdDerived_Games_Embed.json");

        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Game> var = GameClient.getDerivedGames("pd0wq31e", order, true);
        getDerivedGamesEmbedResponseValidation(var);
    }

    @Test
    void getDerivedGamesQueryOrder_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdDerived_Games_Embed.json");

        GamesQuery query = GamesQuery.builder().name("darksouls").build();
        Sorting<GamesOrderBy> order = Sorting.<GamesOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<Game> var = GameClient.getDerivedGames("pd0wq31e", query, order, true);
        getDerivedGamesEmbedResponseValidation(var);
    }

    @Test
    void getGameRecordsTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdRecords.json");

        PagedResponse<Leaderboard> var = GameClient.getGameRecords("pd0wq31e");
        getGameRecordsResponseValidation(var);
    }

    @Test
    void getGameRecordsQueryTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdRecords.json");

        LeaderboardQuery query = LeaderboardQuery.builder().emulators(false).build();
        PagedResponse<Leaderboard> var = GameClient.getGameRecords("pd0wq31e", query);
        getGameRecordsResponseValidation(var);
    }

    @Test
    void getGameRecords_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdRecords_Embed.json");

        PagedResponse<Leaderboard> var = GameClient.getGameRecords("pd0wq31e", true);
        getGameRecordsEmbedResponseValidation(var);
    }

    @Test
    void getGameRecordsQuery_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/GamesByIdRecords_Embed.json");

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
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("y65r071e", var.getResourceList().get(19).getId());
        Assertions.assertEquals("2015-01-03T22:44:26Z", var.getResourceList().get(19).getCreated());
        Assertions.assertEquals("o316x197", var.getResourceList().get(19).getRegions().getEmbedded().get(2).getId());
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