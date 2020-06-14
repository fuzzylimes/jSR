package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JacksonTests.util.DeserializeUtil;
import com.fuzzylimes.jsr.JsrClient;
import com.fuzzylimes.jsr.resources.Guest;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GuestClientTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void getGuestByNameTest() throws IOException, UnexpectedResponseException {
        new MockUp<JsrClient>() {
            @Mock
            public JsonNode getSyncQuery(String url) throws IOException {
                return mapper.readTree(DeserializeUtil.getFile("/responses/Guests.json"));
            }
        };

        Guest var = GuestClient.getGuestByName("Karin");
        Assertions.assertEquals(2, var.getLinks().size());
        Assertions.assertEquals("Karin", var.getName());
    }

    @Test
    void getGuestByNameIT() throws IOException, UnexpectedResponseException {
        Guest var = GuestClient.getGuestByName("Karin");
        Assertions.assertEquals(2, var.getLinks().size());
        Assertions.assertEquals("Karin", var.getName());
    }
}