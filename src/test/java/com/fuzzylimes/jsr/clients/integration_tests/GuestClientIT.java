package com.fuzzylimes.jsr.clients.integration_tests;

import com.fuzzylimes.jsr.clients.GuestClient;
import com.fuzzylimes.jsr.resources.Guest;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class GuestClientIT {

    @Test
    @Disabled
    void getGuestByNameIT() throws IOException, UnexpectedResponseException {
        Guest var = GuestClient.getGuestByName("Karin");
        Assertions.assertEquals(2, var.getLinks().size());
        Assertions.assertEquals("Karin", var.getName());
    }
}