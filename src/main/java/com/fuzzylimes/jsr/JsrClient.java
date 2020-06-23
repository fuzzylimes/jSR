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

/**
 * <p>HTTP client used to make the requests to SpeedRun.com</p>
 *
 * <p>Utilizes OkHttp to make send requests to the SpeedRun.com servers. Takes in the URL and any
 * additional query parameters, and returns back the resulting JSON blob.</p>
 *
 * <p>This is a utility class that is directly referenced by the individual resource clients. There is no
 * need to initialize this class, or directly reference the methods in it, unless you wish to deal with the
 * raw JSON payloads coming back.</p>
 */
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

    /**
     * <p>Used to make a synchronous query to the SpeedRun.com server.</p>
     *
     * <p>Specifically used when only a URL is needed to make the request</p>
     *
     * @param url path to the SpeedRun.com resource to be queried
     * @return A generic {@link JsonNode} representation of the response data
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
    public static JsonNode getSyncQuery(String url) throws IOException, UnexpectedResponseException {
        return getSyncQuery(url, new HashMap<>());
    }

    /**
     * <p>Used to make a synchronous query to the SpeedRun.com server using query parameters.</p>
     *
     * <p>Used when making a request with additional query parameters. Allows for multiple sets of parameter
     * mappings to be passed in the same call. Will construct the final set of parameters before sending query.</p>
     *
     * @param url path to the SpeedRun.com resource to be queried
     * @param qParams a list of query param mappings to be included in the request
     * @return A generic {@link JsonNode} representation of the response data
     * @throws IOException if something goes wrong with mapping
     * @throws UnexpectedResponseException if non-2XX or no body returned to request
     */
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

    public static String buildPath(String base, String... args) {
        return base + String.join("/", args);
    }
}
