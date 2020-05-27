package com.fuzzylimes.jSR.JacksonTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jSR.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jSR.resources.PagedResponse;
import com.fuzzylimes.jSR.resources.Series;
import com.fuzzylimes.jSR.resources.Variable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.fuzzylimes.jSR.resources.ModeratorRoles.SUPER_MODERATOR;
import static com.fuzzylimes.jSR.resources.ScopeTypes.SINGLE_LEVEL;

public class SeriesTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserializeSeriesTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Series.json"));
        PagedResponse<Series> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Series>>() {});
        System.out.println(node);

        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals(".hack", var.getResourceList().get(0).getAbbreviation());
        Assertions.assertEquals(SUPER_MODERATOR, var.getResourceList().get(0).getModerators().getModeratorRoles().get("48ge6kyj"));
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

    @Test
    public void deserializeSeriesEmbedTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Series_Embed.json"));
        PagedResponse<Series> var = mapper.readValue(node.toString(), new TypeReference<PagedResponse<Series>>() {});
        System.out.println(node);

        Assertions.assertEquals(20, var.getResourceList().size());
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals(".hack", var.getResourceList().get(0).getAbbreviation());
        Assertions.assertEquals("68we7q8g", var.getResourceList().get(0).getModerators().getModeratorRolesEmbed().get(0).getId());
        Assertions.assertEquals("next", var.getPagination().getLinks().get(0).getRel());
    }

    @Test
    public void deserializeSeriesByIdTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/SeriesById.json"));
        Series var = mapper.readValue(node.get("data").toString(), Series.class);
        System.out.println(node);

        Assertions.assertEquals(".hack", var.getAbbreviation());
        Assertions.assertEquals(SUPER_MODERATOR, var.getModerators().getModeratorRoles().get("48ge6kyj"));
    }

}
