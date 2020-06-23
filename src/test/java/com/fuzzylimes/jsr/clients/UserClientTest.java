package com.fuzzylimes.jsr.clients;

import com.fuzzylimes.jsr.query_parameters.UserPersonalBestsQuery;
import com.fuzzylimes.jsr.query_parameters.UserQuery;
import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.query_parameters.sorting.UsersOrderBy;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.PersonalBest;
import com.fuzzylimes.jsr.resources.User;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrl;
import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrlAndQueryParams;

class UserClientTest {

    @Test
    void getUsersQueryAndOrderTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Users.json");

        UserQuery query = UserQuery.builder().build();
        Sorting<UsersOrderBy> order = Sorting.<UsersOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<User> var = UserClient.getUsers(query, order);
        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("+", var.getResourceList().get(0).getNames().getInternational());
        Assertions.assertEquals("qxkko37x", var.getResourceList().get(0).getId());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

    @Test
    void getUsersQueryTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Users.json");

        UserQuery query = UserQuery.builder().build();
        PagedResponse<User> var = UserClient.getUsers(query);
        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("+", var.getResourceList().get(0).getNames().getInternational());
        Assertions.assertEquals("qxkko37x", var.getResourceList().get(0).getId());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

    @Test
    void getUsersOrderTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Users.json");

        Sorting<UsersOrderBy> order = Sorting.<UsersOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<User> var = UserClient.getUsers(order);
        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("+", var.getResourceList().get(0).getNames().getInternational());
        Assertions.assertEquals("qxkko37x", var.getResourceList().get(0).getId());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

    @Test
    void getUsersTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Users.json");

        PagedResponse<User> var = UserClient.getUsers();
        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("+", var.getResourceList().get(0).getNames().getInternational());
        Assertions.assertEquals("qxkko37x", var.getResourceList().get(0).getId());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

    @Test
    void getUserByIdTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrl("/responses/UsersById.json");

        User var = UserClient.getUserById("o86vmv3j");
        Assertions.assertEquals(4, var.getLinks().size());
        Assertions.assertEquals("o86vmv3j", var.getId());
        Assertions.assertEquals("https://www.twitch.tv/Elajjaz", var.getTwitch().getUri());
    }

    @Test
    void getRunsForUserQuery_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/UsersByIdPersonalBest_Embed.json");

        UserPersonalBestsQuery query = UserPersonalBestsQuery.builder().top(20).build();
        List<PersonalBest> var = UserClient.getRunsForUser("o86vmv3j", query, true);
        Assertions.assertEquals(10, var.get(1).getPlace());
        Assertions.assertEquals("mk923lvz", var.get(1).getRun().getId());
        Assertions.assertEquals("lde3woe6", var.get(1).getGame().getGameEmbed().getId());
    }

    @Test
    void getRunsForUser_EmbedTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/UsersByIdPersonalBest_Embed.json");

        List<PersonalBest> var = UserClient.getRunsForUser("o86vmv3j", true);
        Assertions.assertEquals(10, var.get(1).getPlace());
        Assertions.assertEquals("mk923lvz", var.get(1).getRun().getId());
        Assertions.assertEquals("lde3woe6", var.get(1).getGame().getGameEmbed().getId());
    }

    @Test
    void getRunsForUserQueryTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/UsersByIdPersonalBest.json");

        UserPersonalBestsQuery query = UserPersonalBestsQuery.builder().top(20).build();
        List<PersonalBest> var = UserClient.getRunsForUser("o86vmv3j", query);
        Assertions.assertEquals(10, var.get(1).getPlace());
        Assertions.assertEquals("mk923lvz", var.get(1).getRun().getId());
        Assertions.assertEquals("lde3woe6", var.get(1).getRun().getGame().getId());
    }

    @Test
    void getRunsForUserTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/UsersByIdPersonalBest.json");

        List<PersonalBest> var = UserClient.getRunsForUser("o86vmv3j");
        Assertions.assertEquals(10, var.get(1).getPlace());
        Assertions.assertEquals("mk923lvz", var.get(1).getRun().getId());
        Assertions.assertEquals("lde3woe6", var.get(1).getRun().getGame().getId());
    }

}