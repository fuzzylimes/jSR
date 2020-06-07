package com.fuzzylimes.jsr.common;

import java.util.HashMap;
import java.util.Map;

public class Properties {

    public static final String USER_AGENT = "jSR";
    public static final String BASE_RESOURCE = "https://www.speedrun.com/api/v1/";
    public static final String GAME_PATH = "games";
    public static final String REGION_PATH = "regions";
    public static final String VARIABLE_PATH = "variables";
    public static final String GUESTS_PATH = "guests";
    public static final String PLATFORMS_PATH = "platforms";
    public static final String CATEGORIES_PATH = "categories";
    public static final String SERIES_PATH = "series";
    public static final String USERS_PATH = "users";
    private static final String EMBED = "embed";
    public static final Map<String, String> CATEGORY_EMBED = new HashMap<>();

    static {
        CATEGORY_EMBED.put(EMBED, "game,variables");

    }


    public static String buildPath(String base, String... args) {
        StringBuilder sb = new StringBuilder(base);
        sb.append(String.join("/", args));
        return sb.toString();
    }
}
