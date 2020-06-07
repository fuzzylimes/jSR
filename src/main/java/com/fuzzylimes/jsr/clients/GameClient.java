package com.fuzzylimes.jsr.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;

public class GameClient {

    private ObjectMapper mapper;
    private OkHttpClient client;

    public GameClient(OkHttpClient client) {
        this.mapper = new ObjectMapper();
        this.client = client;
    }

//    public GameResponse getGameByName(String name) throws IOException {
//        URL url = new URL(Properties.BASE_RESOURCE + Properties.GAME_PATH + "?name=" + name);
//        Request request = new Request.Builder()
//                .url(url)
//                .addHeader("User-Agent", Properties.USER_AGENT)
//                .build();
//
//        Response response = client.newCall(request).execute();
//        return mapper.readValue(response.body().string(), GameResponse.class);
//
//    }
}
