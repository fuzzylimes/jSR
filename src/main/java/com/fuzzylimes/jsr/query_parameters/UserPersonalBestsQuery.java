package com.fuzzylimes.jsr.query_parameters;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Builder to create the query parameters specific to user/{id}/personal-best queries.</p>
 *
 * <h3>Example</h3>
 * {@code UserPersonalBestsQuery query = UserPersonalBestsQuery.builder().top(20).build();}
 */
@Data
@Builder
public class UserPersonalBestsQuery implements QueryParam {

    /** when given, only PBs with a place equal or better than this value (e.g. top=1 returns all World Records of the given user) */
    private Integer top;

    /** when given, restricts the result to games and romhacks in that series; can be either a series ID or abbreviation */
    private String series;

    /** when given, restricts the result to that game; can be either a game ID or abbreviation */
    private String game;


    @Override
    public Map<String, String> getQueryMap() {
        Map<String, String> queryMap = new HashMap<>();
        if (top != null) {
            queryMap.put("top", top.toString());
        }
        if (series != null) {
            queryMap.put("series", series);
        }
        if (game != null) {
            queryMap.put("game", game);
        }
        return queryMap;
    }
}
