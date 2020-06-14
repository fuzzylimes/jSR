package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzzylimes.jsr.common.Properties;
import com.fuzzylimes.jsr.resources.Variable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;

public class VariableClient {
    private ObjectMapper mapper;
    private OkHttpClient client;

    public VariableClient(OkHttpClient client) {
        this.mapper = new ObjectMapper();
        this.client = client;
    }

    /**
     * GET variables/{id}
     *
     * Returns a single variable Object
     * https://github.com/speedruncomorg/api/blob/master/version1/variables.md#get-variablesid
     *
     * @param id id of the variable to query
     * @return Variable object
     * @throws IOException if unable to retrieve response or parse response
     */
    public Variable getVariableById(String id) throws IOException {
        URL url = new URL(Properties.BASE_RESOURCE + Properties.VARIABLES_PATH + "/" + id);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", Properties.USER_AGENT)
                .build();

        Response response = client.newCall(request).execute();
        JsonNode node = mapper.readTree(response.body().string());
        return mapper.readValue(node.get("data").toString(), Variable.class);
    }

}
