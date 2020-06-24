package com.fuzzylimes.jsr.clients;

import com.fuzzylimes.jsr.query_parameters.CategoryRecordsQuery;
import com.fuzzylimes.jsr.resources.*;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrl;
import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrlAndQueryParams;

class CategoryClientTest {

    @Test
    void getCategoryByIdTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrl("/responses/CategoriesById.json");

        Category var = CategoryClient.getCategoryById("jdzme6kv");
        Assertions.assertFalse(var.isMiscellaneous());
        Assertions.assertEquals("jdzme6kv", var.getId());
        Assertions.assertEquals(1, var.getPlayers().getValue());
    }

    @Test
    void getCategoryById_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/CategoriesById_Embed.json");

        Category var = CategoryClient.getCategoryById("jdzme6kv", true);
        Assertions.assertFalse(var.isMiscellaneous());
        Assertions.assertEquals("jdzme6kv", var.getId());
        Assertions.assertEquals(1, var.getPlayers().getValue());
        Assertions.assertEquals(RunTimeTypes.REALTIME, var.getGame().getRuleset().getDefaultTime());
        Assertions.assertTrue(var.getVariables().get(0).isMandatory());
        Assertions.assertTrue(var.getVariables().get(0).getValues().getValues().containsKey("zqomrrp1"));
    }

    @Test
    void getVariablesForCategoryTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/CategoriesByIdVariables.json");

        List<Variable> var = CategoryClient.getVariablesForCategory("jdzme6kv");
        Assertions.assertEquals(ScopeTypes.GLOBAL, var.get(0).getScope().getType());
        Assertions.assertEquals("9l73ympn", var.get(1).getId());
    }

    @Test
    void getCategoriesByIdRecordsTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/CategoriesByIdRecords.json");

        PagedResponse<Leaderboard> var = CategoryClient.getCategoryRecords("jdzme6kv");
        Assertions.assertEquals("pdv0x91w", var.getResourceList().get(0).getGame().getId());
        Assertions.assertEquals(1, var.getResourceList().get(0).getRuns().get(0).getPlace());
        Assertions.assertEquals("yjkv04gm", var.getResourceList().get(0).getRuns().get(0).getRun().getId());
        Assertions.assertEquals("0jm6w6o8", var.getResourceList().get(0).getRuns().get(0).getRun().getStatus().getExaminer());
    }

    @Test
    void getCategoriesByIdRecords_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/CategoriesByIdRecords_Embed.json");

        PagedResponse<Leaderboard> var = CategoryClient.getCategoryRecords("jdzme6kv", true);
        Assertions.assertEquals("pdv0x91w", var.getResourceList().get(0).getGame().getGameEmbed().getId());
        Assertions.assertEquals(1, var.getResourceList().get(0).getRuns().get(0).getPlace());
        Assertions.assertEquals("yjkv04gm", var.getResourceList().get(0).getRuns().get(0).getRun().getId());
        Assertions.assertEquals("0jm6w6o8", var.getResourceList().get(0).getRuns().get(0).getRun().getStatus().getExaminer());
        Assertions.assertEquals("us", var.getResourceList().get(0).getPlayers().getPlayersEmbed().getUserList().get(0).getLocation().getCountry().getCode());
    }

    @Test
    void getCategoriesByIdRecords_QueryTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/CategoriesByIdRecords_Embed.json");

        CategoryRecordsQuery query = CategoryRecordsQuery.builder()
                .skipEmpty(true)
                .top(2)
                .build();

        PagedResponse<Leaderboard> var = CategoryClient.getCategoryRecords("jdzme6kv", true, query);
        Assertions.assertEquals("pdv0x91w", var.getResourceList().get(0).getGame().getGameEmbed().getId());
        Assertions.assertEquals(1, var.getResourceList().get(0).getRuns().get(0).getPlace());
        Assertions.assertEquals("yjkv04gm", var.getResourceList().get(0).getRuns().get(0).getRun().getId());
        Assertions.assertEquals("0jm6w6o8", var.getResourceList().get(0).getRuns().get(0).getRun().getStatus().getExaminer());
        Assertions.assertEquals("us", var.getResourceList().get(0).getPlayers().getPlayersEmbed().getUserList().get(0).getLocation().getCountry().getCode());
    }
}