package com.fuzzylimes.jsr.JacksonTests.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.JsrClient;
import mockit.Mock;
import mockit.MockUp;

import java.io.IOException;
import java.util.Map;

public class ClientTestUtil {

    private static ObjectMapper mapper = new ObjectMapper();


    public static void MockJsrClientUrl(String file) {
        new MockUp<JsrClient>() {
            @Mock
            public JsonNode getSyncQuery(String url) throws IOException {
                return mapper.readTree(DeserializeUtil.getFile(file));
            }
        };
    }

    public static void MockJsrClientUrlAndQueryParams(String file) {
        new MockUp<JsrClient>() {
            @Mock
            public JsonNode getSyncQuery(String url, Map<String, String>... qParams) throws IOException {
                return mapper.readTree(DeserializeUtil.getFile(file));
            }
        };
    }
}
