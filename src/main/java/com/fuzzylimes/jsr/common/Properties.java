package com.fuzzylimes.jsr.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Internal Util class for property constants.
 */
public class Properties {
    // Main resources
    public static final String USER_AGENT = "jSR";
    public static final String BASE_RESOURCE = "https://www.speedrun.com/api/v1/";
    // Path values
    public static final String GAME_PATH = "games";
    public static final String DEVELOPERS_PATH = "developers";
    public static final String DERIVED_GAMES_PATH = "derived-games";
    public static final String ENGINES_PATH = "engines";
    public static final String GAME_TYPES_PATH = "gametypes";
    public static final String GENRES_PATH = "genres";
    public static final String PUBLISHERS_PATH = "publishers";
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
    public static final String PERSONAL_BESTS_PATH = "personal-bests";
    public static final String RUNS_PATH = "runs";
    public static final String LEADERBOARD_PATH = "leaderboards";
    // Embed Strings
    public static final String CATEGORY_EMBED_VALUES = "game,variables";
    public static final String LEADERBOARD_EMBED_VALUES = "game,category,level,players,regions,platforms,variables";
    public static final String GAME_EMBED_VALUES = "levels,categories,moderators,gametypes,platforms,regions,genres,engines,developers,publishers,variables";
    public static final String LEVEL_EMBED_VALUES = "categories,variables";
    public static final String RUNS_EMBED_VALUES = "game,category,level,players,region,platform";
    public static final String SERIES_EMBED_VALUES = "moderators";
    private static final String EMBED = "embed";
    private static final String BULK = "_bulk";
    // Embed Maps
    private static final Map<String, String> CATEGORY_EMBED = new HashMap<>();
    private static final Map<String, String> LEADERBOARD_EMBED = new HashMap<>();
    private static final Map<String, String> LEVEL_EMBED = new HashMap<>();
    private static final Map<String, String> RUNS_EMBED = new HashMap<>();
    private static final Map<String, String> SERIES_EMBED = new HashMap<>();
    private static final Map<String, String> GAME_EMBED = new HashMap<>();
    private static final Map<String, String> BULK_EMBED = new HashMap<>();
    static {
        CATEGORY_EMBED.put(EMBED, CATEGORY_EMBED_VALUES);
        LEADERBOARD_EMBED.put(EMBED, LEADERBOARD_EMBED_VALUES);
        LEVEL_EMBED.put(EMBED, LEVEL_EMBED_VALUES);
        RUNS_EMBED.put(EMBED, RUNS_EMBED_VALUES);
        SERIES_EMBED.put(EMBED, SERIES_EMBED_VALUES);
        GAME_EMBED.put(EMBED, GAME_EMBED_VALUES);
        BULK_EMBED.put(BULK, "true");
    }

    public static Map<String,String> getCategoryEmbed(){
        return CATEGORY_EMBED;
    }

    public static Map<String,String> getLeaderboardEmbed(){
        return LEADERBOARD_EMBED;
    }

    public static Map<String,String> getLevelEmbed(){
        return LEVEL_EMBED;
    }

    public static Map<String,String> getRunsEmbed(){
        return RUNS_EMBED;
    }

    public static Map<String,String> getSeriesEmbed(){
        return SERIES_EMBED;
    }

    public static Map<String,String> getGameEmbed(){
        return GAME_EMBED;
    }

    public static Map<String,String> getBulkEmbed(){
        return BULK_EMBED;
    }

    private Properties() {
        // This is an internal util class
    }
}
