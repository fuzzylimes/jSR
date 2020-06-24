package com.fuzzylimes.jsr.query_parameters;

import com.fuzzylimes.jsr.resources.RunStatusTypes;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Builder to create the query parameters specific to Runs queries.</p>
 *
 * <h3>Example</h3>
 * {@code RunsQuery query = RunsQuery.builder().emulated(false).build();}
 */
@Data
@Builder
public class RunsQuery implements QueryParam {

    /** user ID; when given, only returns runs played by that user */
    private String user;

    /** when given, only returns runs done by that guest */
    private String guest;

    /** user ID; when given, only returns runs examined by that user */
    private String examiner;

    /** game ID; when given, restricts to that game */
    private String game;

    /** level ID; when given, restricts to that level */
    private String level;

    /** category ID; when given, restricts to that category */
    private String category;

    /** platform ID; when given, restricts to that platform */
    private String platform;

    /** region ID; when given, restricts to that region */
    private String region;

    /** when 1, yes or true, only games run on emulator will be returned */
    private Boolean emulated;

    /** filters by run status; new, verified and rejected are possible values for this parameter */
    private RunStatusTypes statusTypes;

    @Override
    public Map<String, String> getQueryMap() {
        Map<String, String> queryMap = new HashMap<>();
        if(user != null) {
            queryMap.put("user", user);
        }
        if(guest != null) {
            queryMap.put("guest", guest);
        }
        if(examiner != null) {
            queryMap.put("examiner", examiner);
        }
        if(game != null) {
            queryMap.put("game", game);
        }
        if(level != null) {
            queryMap.put("level", level);
        }
        if(category != null) {
            queryMap.put("category", category);
        }
        if(platform != null) {
            queryMap.put("platform", platform);
        }
        if(region != null) {
            queryMap.put("region", region);
        }
        if(emulated != null) {
            queryMap.put("emulated", emulated.toString());
        }
        if(statusTypes != null) {
            queryMap.put("statusTypes", statusTypes.toString());
        }
        return queryMap;
    }
}
