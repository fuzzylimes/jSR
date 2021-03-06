package com.fuzzylimes.jsr.clients;

import com.fuzzylimes.jsr.resources.Guest;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrl;

class GuestClientTest {

    @Test
    void getGuestByNameTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrl("/responses/Guests.json");

        Guest var = GuestClient.getGuestByName("Karin");
        Assertions.assertEquals(2, var.getLinks().size());
        Assertions.assertEquals("Karin", var.getName());
    }

}