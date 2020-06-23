package com.fuzzylimes.jsr.clients.integration_tests;

import com.fuzzylimes.jsr.clients.UserClient;
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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

@Disabled
class UserClientIT {

    @Test
    void getUsersQueryAndOrderIT() throws IOException, UnexpectedResponseException {
        UserQuery query = UserQuery.builder().twitch("Elajjaz").build();
        Sorting<UsersOrderBy> order = Sorting.<UsersOrderBy>builder().direction(Direction.ASCCENDING).build();
        PagedResponse<User> var = UserClient.getUsers(query, order);
        Assertions.assertEquals(1, var.getResourceList().size());
        Assertions.assertEquals(1, var.getPagination().getSize());
        Assertions.assertEquals("o86vmv3j", var.getResourceList().get(0).getId());
    }

    @Test
    void getUserByIdIT() throws IOException, UnexpectedResponseException {
        User var = UserClient.getUserById("o86vmv3j");
        Assertions.assertEquals(4, var.getLinks().size());
        Assertions.assertEquals("o86vmv3j", var.getId());
        Assertions.assertEquals("https://www.twitch.tv/Elajjaz", var.getTwitch().getUri());
    }

    @Test
    void getRunsForUserQuery_EmbedIT() throws IOException, UnexpectedResponseException {
        UserPersonalBestsQuery query = UserPersonalBestsQuery.builder().top(20).build();
        List<PersonalBest> var = UserClient.getRunsForUser("o86vmv3j", query, true);
        Assertions.assertEquals(10, var.get(1).getPlace());
        Assertions.assertEquals("mk923lvz", var.get(1).getRun().getId());
        Assertions.assertEquals("lde3woe6", var.get(1).getGame().getGameEmbed().getId());
    }

}