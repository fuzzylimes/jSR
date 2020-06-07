package com.fuzzylimes.jsr;

import com.fuzzylimes.jsr.clients.*;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Region;
import com.fuzzylimes.jsr.resources.Variable;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class JsrConnection {

    private GameClient gameClient;
    private RegionClient regionClient;
    private OkHttpClient httpClient;
    private VariableClient variableClient;
    private GuestClient guestClient;
    private PlatformClient platformClient;
    private SeriesClient seriesClient;

    public JsrConnection() {
        this.httpClient = new OkHttpClient();
        this.gameClient = new GameClient(this.httpClient);
        this.regionClient = new RegionClient(this.httpClient);
        this.variableClient = new VariableClient(this.httpClient);
        this.guestClient = new GuestClient(this.httpClient);
        this.platformClient = new PlatformClient(this.httpClient);
        this.seriesClient = new SeriesClient(this.httpClient);
    }

    public GameClient getGameClient() {
        return this.gameClient;
    }

//    public GameResponse getGame(String title) throws IOException {
//        return gameClient.getGameByName(title, httpClient);
//    }

    public PagedResponse<Region> getRegions() throws IOException {
        return regionClient.getRegions();
    }

    public Region getRegion(String id) throws IOException {
        return regionClient.getRegion(id);
    }

    public Variable getVariable(String id) throws IOException {
        return variableClient.getVariableById(id);
    }
}
