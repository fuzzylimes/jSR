package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.JsrClient;
import com.fuzzylimes.jsr.query_parameters.LevelCategoriesQuery;
import com.fuzzylimes.jsr.query_parameters.LevelLeaderboardsQuery;
import com.fuzzylimes.jsr.query_parameters.sorting.CategoriesOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.query_parameters.sorting.VariablesOrderBy;
import com.fuzzylimes.jsr.resources.*;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import jdk.nashorn.internal.ir.annotations.Ignore;
import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.fuzzylimes.jsr.resources.CategoryTypes.PER_LEVEL;

class LevelsClientTest {


    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getLevelByIdTest() throws IOException, UnexpectedResponseException {
        new MockUp<JsrClient>() {
          @Mock
          public JsonNode getSyncQuery(String url) throws IOException {
              return mapper.readTree(DeserializeUtil.getFile("/responses/LevelsById.json"));
          }
        };

        Level response = LevelsClient.getLevelById("5d74ypvd");
        Assertions.assertEquals("5d74ypvd", response.getId());
        Assertions.assertEquals(7, response.getLinks().size());
    }

    @Test
    public void getLevelById_EmbedTest() throws IOException, UnexpectedResponseException {
        new MockUp<JsrClient>() {
            @Mock
            public JsonNode getSyncQuery(String url, Map<String, String>... qParams) throws IOException {
                return mapper.readTree(DeserializeUtil.getFile("/responses/LevelsById_Embed.json"));
            }
        };

        Level var = LevelsClient.getLevelById("5d74ypvd", true);
        Assertions.assertEquals("5d74ypvd", var.getId());
        Assertions.assertEquals(7, var.getLinks().size());
        Assertions.assertEquals(2, var.getCategories().size());
        Assertions.assertEquals(PER_LEVEL, var.getCategories().get(1).getType());
        Assertions.assertEquals(1, var.getVariables().size());
        Assertions.assertFalse(var.getVariables().get(0).isSubcategory());
    }

    // GET levels/{id}/categories
    @Test
    public void getCategoriesByLevelId_querySortTest() throws IOException, UnexpectedResponseException {
        new MockUp<JsrClient>() {
            @Mock
            public JsonNode getSyncQuery(String url, Map<String, String>... qParams) throws IOException {
                return mapper.readTree(DeserializeUtil.getFile("/responses/LevelsByIdCategories.json"));
            }
        };

        LevelCategoriesQuery query = LevelCategoriesQuery.builder().miscellaneous(false).build();
        Sorting<CategoriesOrderBy> sorting = Sorting.<CategoriesOrderBy>builder().direction(Direction.ASCCENDING).orderBy(CategoriesOrderBy.POS).build();

        List<Category> var = LevelsClient.getCategoriesForLevelId("5d74ypvd", query, sorting);
        Assertions.assertEquals(2, var.size());
        Assertions.assertEquals("7dgrergk", var.get(0).getId());
        Assertions.assertEquals(PER_LEVEL, var.get(0).getType());
    }

    @Test
    public void getCategoriesByLevelId_sortTest() throws IOException, UnexpectedResponseException {
        new MockUp<JsrClient>() {
            @Mock
            public JsonNode getSyncQuery(String url, Map<String, String>... qParams) throws IOException {
                return mapper.readTree(DeserializeUtil.getFile("/responses/LevelsByIdCategories.json"));
            }
        };

        Sorting<CategoriesOrderBy> sorting = Sorting.<CategoriesOrderBy>builder().direction(Direction.ASCCENDING).orderBy(CategoriesOrderBy.POS).build();

        List<Category> var = LevelsClient.getCategoriesForLevelId("5d74ypvd", sorting);
        Assertions.assertEquals(2, var.size());
        Assertions.assertEquals("7dgrergk", var.get(0).getId());
        Assertions.assertEquals(PER_LEVEL, var.get(0).getType());
    }

    @Test
    public void getCategoriesByLevelId_queryTest() throws IOException, UnexpectedResponseException {
        new MockUp<JsrClient>() {
            @Mock
            public JsonNode getSyncQuery(String url, Map<String, String>... qParams) throws IOException {
                return mapper.readTree(DeserializeUtil.getFile("/responses/LevelsByIdCategories.json"));
            }
        };

        LevelCategoriesQuery query = LevelCategoriesQuery.builder().miscellaneous(false).build();

        List<Category> var = LevelsClient.getCategoriesForLevelId("5d74ypvd", query);
        Assertions.assertEquals(2, var.size());
        Assertions.assertEquals("7dgrergk", var.get(0).getId());
        Assertions.assertEquals(PER_LEVEL, var.get(0).getType());
    }

    @Test
    public void getCategoriesByLevelIdTest() throws IOException, UnexpectedResponseException {
        new MockUp<JsrClient>() {
            @Mock
            public JsonNode getSyncQuery(String url) throws IOException {
                return mapper.readTree(DeserializeUtil.getFile("/responses/LevelsByIdCategories.json"));
            }
        };

        List<Category> var = LevelsClient.getCategoriesForLevelId("5d74ypvd");
        Assertions.assertEquals(2, var.size());
        Assertions.assertEquals("7dgrergk", var.get(0).getId());
        Assertions.assertEquals(PER_LEVEL, var.get(0).getType());
    }

    // GET levels/{id}/variables
    @Test
    public void getVariablesByLevelId_sortTest() throws IOException, UnexpectedResponseException {
        new MockUp<JsrClient>() {
            @Mock
            public JsonNode getSyncQuery(String url, Map<String, String>... qParams) throws IOException {
                return mapper.readTree(DeserializeUtil.getFile("/responses/LevelsByIdVariables.json"));
            }
        };

        Sorting<VariablesOrderBy> sorting = Sorting.<VariablesOrderBy>builder().direction(Direction.ASCCENDING).orderBy(VariablesOrderBy.POS).build();

        List<Variable> var = LevelsClient.getVaribaleForLevelId("5d74ypvd", sorting);
        Assertions.assertEquals(1, var.size());
        Assertions.assertEquals("0nw2mjdn", var.get(0).getId());
        Assertions.assertNull(var.get(0).getValues().getDefaultValue());
        Assertions.assertEquals("Luigi", var.get(0).getValues().getValues().get("0q5oeer1").getLabel());
    }

    @Test
    public void getVariablesByLevelIdest() throws IOException, UnexpectedResponseException {
        new MockUp<JsrClient>() {
            @Mock
            public JsonNode getSyncQuery(String url) throws IOException {
                return mapper.readTree(DeserializeUtil.getFile("/responses/LevelsByIdVariables.json"));
            }
        };

        List<Variable> var = LevelsClient.getVaribaleForLevelId("5d74ypvd");
        Assertions.assertEquals(1, var.size());
        Assertions.assertEquals("0nw2mjdn", var.get(0).getId());
        Assertions.assertNull(var.get(0).getValues().getDefaultValue());
        Assertions.assertEquals("Luigi", var.get(0).getValues().getValues().get("0q5oeer1").getLabel());
    }

    // GET levels/{id}/records
    @Test
    public void getRecordsByLevelId_embedQueryTest() throws IOException, UnexpectedResponseException {
        new MockUp<JsrClient>() {
            @Mock
            public JsonNode getSyncQuery(String url, Map<String, String>... qParams) throws IOException {
                return mapper.readTree(DeserializeUtil.getFile("/responses/LevelsByIdRecords_Embed.json"));
            }
        };

        LevelLeaderboardsQuery query = LevelLeaderboardsQuery.builder().top(3).skipEmpty(false).build();

        PagedResponse<Leaderboard> var = LevelsClient.getLeaderboardForLevelId("5d74ypvd", true, query);
        Assertions.assertEquals(2, var.getResourceList().size());
        Assertions.assertEquals("5d74ypvd", var.getResourceList().get(0).getLevel().getLevelEmbed().getId());
        Assertions.assertNull(var.getResourceList().get(1).getPlatform());
        Assertions.assertEquals("v8lnen2x", var.getResourceList().get(1).getRuns().get(0).getRun().getPlayers().getPlayers().get(0).getId());
    }

    @Test
    public void getRecordsByLevelId_embedTest() throws IOException, UnexpectedResponseException {
        new MockUp<JsrClient>() {
            @Mock
            public JsonNode getSyncQuery(String url, Map<String, String>... qParams) throws IOException {
                return mapper.readTree(DeserializeUtil.getFile("/responses/LevelsByIdRecords_Embed.json"));
            }
        };

        PagedResponse<Leaderboard> var = LevelsClient.getLeaderboardForLevelId("5d74ypvd", true);
        Assertions.assertEquals(2, var.getResourceList().size());
        Assertions.assertEquals("5d74ypvd", var.getResourceList().get(0).getLevel().getLevelEmbed().getId());
        Assertions.assertNull(var.getResourceList().get(1).getPlatform());
        Assertions.assertEquals("v8lnen2x", var.getResourceList().get(1).getRuns().get(0).getRun().getPlayers().getPlayers().get(0).getId());
    }

    @Test
    public void getRecordsByLevelId_QueryTest() throws IOException, UnexpectedResponseException {
        new MockUp<JsrClient>() {
            @Mock
            public JsonNode getSyncQuery(String url, Map<String, String>... qParams) throws IOException {
                return mapper.readTree(DeserializeUtil.getFile("/responses/LevelsByIdRecords.json"));
            }
        };

        LevelLeaderboardsQuery query = LevelLeaderboardsQuery.builder().top(3).skipEmpty(false).build();

        PagedResponse<Leaderboard> var = LevelsClient.getLeaderboardForLevelId("5d74ypvd", query);
        Assertions.assertEquals(2, var.getResourceList().size());
        Assertions.assertEquals("5d74ypvd", var.getResourceList().get(0).getLevel().getId());
        Assertions.assertNull(var.getResourceList().get(1).getPlatform());
        Assertions.assertEquals("v8lnen2x", var.getResourceList().get(1).getRuns().get(0).getRun().getPlayers().getPlayers().get(0).getId());
    }

    @Test
    public void getRecordsByLevelIdTest() throws IOException, UnexpectedResponseException {
        new MockUp<JsrClient>() {
            @Mock
            public JsonNode getSyncQuery(String url, Map<String, String>... qParams) throws IOException {
                return mapper.readTree(DeserializeUtil.getFile("/responses/LevelsByIdRecords.json"));
            }
        };

        PagedResponse<Leaderboard> var = LevelsClient.getLeaderboardForLevelId("5d74ypvd");
        Assertions.assertEquals(2, var.getResourceList().size());
        Assertions.assertEquals("5d74ypvd", var.getResourceList().get(0).getLevel().getId());
        Assertions.assertNull(var.getResourceList().get(1).getPlatform());
        Assertions.assertEquals("v8lnen2x", var.getResourceList().get(1).getRuns().get(0).getRun().getPlayers().getPlayers().get(0).getId());
    }


    // Integration Tests
    @Test
    @Ignore
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
    @Ignore
    public void getCategoriesByLevelId_querySortIT() throws IOException, UnexpectedResponseException {
        LevelCategoriesQuery query = LevelCategoriesQuery.builder().miscellaneous(false).build();
        Sorting<CategoriesOrderBy> sorting = Sorting.<CategoriesOrderBy>builder().direction(Direction.ASCCENDING).orderBy(CategoriesOrderBy.POS).build();

        List<Category> var = LevelsClient.getCategoriesForLevelId("5d74ypvd", query, sorting);
        Assertions.assertEquals(2, var.size());
        Assertions.assertEquals("7dgrergk", var.get(0).getId());
        Assertions.assertEquals(PER_LEVEL, var.get(0).getType());
    }

    @Test
    @Ignore
    public void getRecordsByLevelId_embedQueryIT() throws IOException, UnexpectedResponseException {

        LevelLeaderboardsQuery query = LevelLeaderboardsQuery.builder().top(3).skipEmpty(false).build();

        PagedResponse<Leaderboard> var = LevelsClient.getLeaderboardForLevelId("5d74ypvd", true, query);
        Assertions.assertEquals(2, var.getResourceList().size());
        Assertions.assertEquals("5d74ypvd", var.getResourceList().get(0).getLevel().getLevelEmbed().getId());
        Assertions.assertNull(var.getResourceList().get(1).getPlatform());
        Assertions.assertEquals("v8lnen2x", var.getResourceList().get(1).getRuns().get(0).getRun().getPlayers().getPlayers().get(0).getId());
    }

}