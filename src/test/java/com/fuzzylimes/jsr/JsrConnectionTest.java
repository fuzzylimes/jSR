package com.fuzzylimes.jsr;

import com.fuzzylimes.jsr.resources.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsrConnectionTest {

//    @Test
//    public void getGameByName() throws IOException {
//        JsrConnection connection = new JsrConnection();
//        GameResponse val = connection.getGame("Dark Souls III");
//        assertNotNull(val);
//    }

    @Test
    public void getRegions() throws IOException {
        JsrConnection connection = new JsrConnection();
        PagedResponse<Region> val = connection.getRegions();
        assertNotNull(val);
    }

    @Test
    public void getRegion() throws IOException {
        JsrConnection connection = new JsrConnection();
        Region val = connection.getRegion("pr184lqn");
        assertNotNull(val);
    }

    @Test
    public void getVariables() throws IOException {
        JsrConnection connection = new JsrConnection();
        Variable val = connection.getVariable("dlomqkj8");
        assertNotNull(val);
        assertEquals(ScopeTypes.SINGLE_LEVEL, val.getScope().getType());
    }

}