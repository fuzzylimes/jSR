package com.fuzzylimes.jsr.JacksonTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.resources.Guest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GuestsTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserializeGuestsTest() throws IOException {
        JsonNode node = mapper.readTree(DeserializeUtil.getFile("/responses/Guests.json"));
        Guest var = mapper.readValue(node.get("data").toString(), Guest.class);

        Assertions.assertEquals(2, var.getLinks().size());
        Assertions.assertEquals("Karin", var.getName());
    }
}
