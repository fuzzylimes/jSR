package com.fuzzylimes.jsr.JacksonTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.resources.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class UsersTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserializeUsersTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Users.json"));
        PagedResponse<User> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<User>>() {});

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

        Assertions.assertEquals(4, var.getLinks().size());
        Assertions.assertEquals("o86vmv3j", var.getId());
        Assertions.assertEquals("https://www.twitch.tv/Elajjaz", var.getTwitch().getUri());
    }


    @Test
    public void deserializeUserRunsTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/UsersByIdPersonalBest.json"));
        List<PersonalBest> var = mapper.readValue(node.get("data").toString(), new TypeReference<List<PersonalBest>>() {});

        Assertions.assertEquals(10, var.get(1).getPlace());
        Assertions.assertEquals("mk923lvz", var.get(1).getRun().getId());
        Assertions.assertEquals("lde3woe6", var.get(1).getRun().getGame().getId());
    }

    @Test
    public void deserializeUserRuns_EmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/UsersByIdPersonalBest_Embed.json"));
        List<PersonalBest> var = mapper.readValue(node.get("data").toString(), new TypeReference<List<PersonalBest>>() {});

        Assertions.assertEquals(10, var.get(1).getPlace());
        Assertions.assertEquals("mk923lvz", var.get(1).getRun().getId());
        Assertions.assertEquals("lde3woe6", var.get(1).getGame().getGameEmbed().getId());
    }
}
