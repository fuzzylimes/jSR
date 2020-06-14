package com.fuzzylimes.jsr.common;

import java.util.HashMap;
import java.util.Map;

public class Properties {

    public static final String USER_AGENT = "jSR";
    public static final String BASE_RESOURCE = "https://www.speedrun.com/api/v1/";
    // Path values
    public static final String GAME_PATH = "games";
    public static final String REGION_PATH = "regions";
    public static final String VARIABLES_PATH = "variables";
    public static final String GUESTS_PATH = "guests";
    public static final String PLATFORMS_PATH = "platforms";
    public static final String CATEGORIES_PATH = "categories";
    public static final String CATEGORY_PATH = "category";
    public static final String SERIES_PATH = "series";
    public static final String USERS_PATH = "users";
    public static final String LEVEL_PATH = "level";
    public static final String LEVELS_PATH = "levels";
    public static final String RECORDS_PATH = "records";
    public static final String LEADERBOARD_PATH = "leaderboards";
    // Embed Strings
    public static final String CATEGORY_EMBED_VALUES = "game,variables";
    public static final String LEADERBOARD_EMBED_VALUES = "game,category,level,players,regions,platforms,variables";
    public static final String LEVEL_EMBED_VALUES = "categories,variables";
    private static final String EMBED = "embed";
    // Embed Maps
    public static final Map<String, String> CATEGORY_EMBED = new HashMap<>();
    public static final Map<String, String> LEADERBOARD_EMBED = new HashMap<>();
    public static final Map<String, String> LEVEL_EMBED = new HashMap<>();

    static {
        CATEGORY_EMBED.put(EMBED, CATEGORY_EMBED_VALUES);
        LEADERBOARD_EMBED.put(EMBED, LEADERBOARD_EMBED_VALUES);
        LEVEL_EMBED.put(EMBED, LEVEL_EMBED_VALUES);
    }


    public static String buildPath(String base, String... args) {
        StringBuilder sb = new StringBuilder(base);
        sb.append(String.join("/", args));
        return sb.toString();
    }
}
