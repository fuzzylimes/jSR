package com.fuzzylimes.jsr;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.common.Properties;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JsrClient {

    private JsrClient() {
        // Not used
    }

    //TODO: Add caching to this at some point
    private static final OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
    public static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode getSyncQuery(String url) throws IOException, UnexpectedResponseException {
        return getSyncQuery(url, new HashMap<>());
    }

    @SafeVarargs
    public static JsonNode getSyncQuery(String url, Map<String, String>... qParams) throws IOException, UnexpectedResponseException {

        if (url == null) {
            throw new IllegalArgumentException("url must not be null");
        }

        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        if (qParams != null) {
            for (Map<String, String> qParam : qParams) {
                for (Map.Entry<String, String> param : qParam.entrySet()) {
                    httpBuilder.addQueryParameter(param.getKey(), param.getValue());
                }
            }
        }

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader("User-Agent", Properties.USER_AGENT)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful() && response.body() != null) {
            JsonNode result = mapper.readTree(response.body().string());
            response.close();
            return result;
        } else {
            response.close();
            throw new UnexpectedResponseException("Non-2XX response recieved: " + response.code());
        }
    }
}
