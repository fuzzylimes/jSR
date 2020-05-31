package com.fuzzylimes.jSR.JacksonTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jSR.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jSR.resources.PagedResponse;
import com.fuzzylimes.jSR.resources.Platform;
import com.fuzzylimes.jSR.resources.Run;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RunsTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserializeRunsTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Runs.json"));
        PagedResponse<Run> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Run>>() {});
        System.out.println(node);

        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
        Assertions.assertEquals("1wzpqgyq", var.getResourceList().get(0).getId());
        Assertions.assertEquals("nj1ne1p4", var.getResourceList().get(0).getGame().getId());
        Assertions.assertEquals("v48grxpr", var.getResourceList().get(0).getPlayers().getPlayers().get(0).getId());
    }

    @Test
    public void deserializeRunsEmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Runs_Embed.json"));
        PagedResponse<Run> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Run>>() {});
        System.out.println(node);

        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
        Assertions.assertEquals("1wzpqgyq", var.getResourceList().get(0).getId());
        Assertions.assertEquals("nj1ne1p4", var.getResourceList().get(0).getGame().getGameEmbed().getId());
        Assertions.assertEquals("v48grxpr", var.getResourceList().get(0).getPlayers().getPlayersEmbed().getUserList().get(0).getId());
    }

    @Test
    public void deserializeRunsByIdTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/RunsById.json"));
        Run var = mapper.readValue(node.get("data").toString(), Run.class);
        System.out.println(node);

        Assertions.assertEquals("1wzpqgyq", var.getId());
        Assertions.assertEquals("nj1ne1p4", var.getGame().getId());
        Assertions.assertEquals("v48grxpr", var.getPlayers().getPlayers().get(0).getId());
    }

    @Test
    public void deserializeRunsByIdEmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/RunsById_Embed.json"));
        Run var = mapper.readValue(node.get("data").toString(), Run.class);
        System.out.println(node);

        Assertions.assertEquals("1wzpqgyq", var.getId());
        Assertions.assertEquals("nj1ne1p4", var.getGame().getGameEmbed().getId());
        Assertions.assertEquals("v48grxpr", var.getPlayers().getPlayersEmbed().getUserList().get(0).getId());
    }
}
