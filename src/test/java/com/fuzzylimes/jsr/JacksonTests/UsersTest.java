package com.fuzzylimes.jsr.JacksonTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.resources.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.System;
import java.util.List;

public class UsersTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserializeUsersTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Users.json"));
        PagedResponse<User> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<User>>() {});
        System.out.println(node);

        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("+", var.getResourceList().get(0).getNames().getInternational());
        Assertions.assertEquals("qxkko37x", var.getResourceList().get(0).getId());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

    @Test
    public void deserializeUserByIdTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/UsersById.json"));
        User var = mapper.readValue(node.get("data").toString(), User.class);
        System.out.println(node);

        Assertions.assertEquals(4, var.getLinks().size());
        Assertions.assertEquals("o86vmv3j", var.getId());
        Assertions.assertEquals("https://www.twitch.tv/Elajjaz", var.getTwitch().getUri());
    }


    @Test
    public void deserializeUsersEmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/UsersByIdPersonalBest.json"));
        List<PersonalBest> var = mapper.readValue(node.get("data").toString(), new TypeReference<List<PersonalBest>>() {});
        System.out.println(node);

        Assertions.assertEquals(10, var.get(1).getPlace());
        Assertions.assertEquals("mk923lvz", var.get(1).getRun().getId());
        Assertions.assertEquals("lde3woe6", var.get(1).getRun().getGame().getId());
    }
}
