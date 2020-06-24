package com.fuzzylimes.jsr.query_parameters;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Builder to create the query parameters specific to performing Leaderboard queries.</p>
 *
 * <h3>Example</h3>
 * {@code LeaderboardQuery query = LeaderboardQuery.builder().top(10).emulators(false).build();}
 */
@Data
@Builder
public class LeaderboardQuery implements QueryParam {

    /** only return the top N places (this can result in more than N runs!) */
    private Integer top;

    /** platform ID; when given, only returns runs done on that particular platform */
    private String platform;

    /** region ID; when given, only returns runs done in that particular region */
    private String region;

    /**
     * when not given, real devices and emulators are shown. When set to a true value,
     * only emulators are shown, else only real devices are shown
     */
    private Boolean emulators;

    /** false by default; when set to a true value, only runs with a video will be returned */
    private Boolean videoOnly;

    /** controls the sorting; can be one of realtime, realtime_noloads or ingame */
    private String timing;

    /** ISO 8601 date string; when given, only returns runs done before or on this date */
    private String date;

    /**
     * additional custom variable values
     * To filter by custom variables, send a map where the key is the variable ID and the value ID is the value ID.
     */
    private Map<String, String> variables;


    @Override
    public Map<String, String> getQueryMap() {
        Map<String, String> queryMap = new HashMap<>();
        if (top != null) {
            queryMap.put("top", top.toString());
        }
        if (platform != null) {
            queryMap.put("platform", platform);
        }
        if (region != null) {
            queryMap.put("region", region);
        }
        if (emulators != null) {
            queryMap.put("emulators", emulators.toString());
        }
        if (videoOnly != null) {
            queryMap.put("videoOnly", videoOnly.toString());
        }
        if (timing != null) {
            queryMap.put("timing", timing);
        }
        if (date != null) {
            queryMap.put("date", date);
        }
        if (variables != null) {
            queryMap.putAll(variables);
        }
        return queryMap;
    }


}
