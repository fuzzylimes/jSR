package com.fuzzylimes.jsr.clients.integration_tests;

import com.fuzzylimes.jsr.clients.LevelsClient;
import com.fuzzylimes.jsr.query_parameters.LevelCategoriesQuery;
import com.fuzzylimes.jsr.query_parameters.LevelLeaderboardsQuery;
import com.fuzzylimes.jsr.query_parameters.sorting.CategoriesOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.Category;
import com.fuzzylimes.jsr.resources.Leaderboard;
import com.fuzzylimes.jsr.resources.Level;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.fuzzylimes.jsr.resources.CategoryTypes.PER_LEVEL;

class LevelsClientIT {

    @Test
    @Disabled
    public void getLevelById_EmbedIT() throws IOException, UnexpectedResponseException {
        Level var = LevelsClient.getLevelById("5d74ypvd", true);
        Assertions.assertEquals("5d74ypvd", var.getId());
        Assertions.assertEquals(7, var.getLinks().size());
        Assertions.assertEquals(2, var.getCategories().size());
        Assertions.assertEquals(PER_LEVEL, var.getCategories().get(1).getType());
        Assertions.assertEquals(1, var.getVariables().size());
        Assertions.assertFalse(var.getVariables().get(0).isSubcategory());
    }

    @Test
    @Disabled
    public void getCategoriesByLevelId_querySortIT() throws IOException, UnexpectedResponseException {
        LevelCategoriesQuery query = LevelCategoriesQuery.builder().miscellaneous(false).build();
        Sorting<CategoriesOrderBy> sorting = Sorting.<CategoriesOrderBy>builder().direction(Direction.ASCCENDING).orderBy(CategoriesOrderBy.POS).build();

        List<Category> var = LevelsClient.getCategoriesForLevelId("5d74ypvd", query, sorting);
        Assertions.assertEquals(2, var.size());
        Assertions.assertEquals("7dgrergk", var.get(0).getId());
        Assertions.assertEquals(PER_LEVEL, var.get(0).getType());
    }

    @Test
    @Disabled
    public void getRecordsByLevelId_embedQueryIT() throws IOException, UnexpectedResponseException {

        LevelLeaderboardsQuery query = LevelLeaderboardsQuery.builder().top(3).skipEmpty(false).build();

        PagedResponse<Leaderboard> var = LevelsClient.getLeaderboardForLevelId("5d74ypvd", true, query);
        Assertions.assertEquals(2, var.getResourceList().size());
        Assertions.assertEquals("5d74ypvd", var.getResourceList().get(0).getLevel().getLevelEmbed().getId());
        Assertions.assertNull(var.getResourceList().get(1).getPlatform());
        Assertions.assertEquals("v8lnen2x", var.getResourceList().get(1).getRuns().get(0).getRun().getPlayers().getPlayers().get(0).getId());
    }

}