package com.fuzzylimes.jsr.query_parameters;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Builder to create the query parameters specific to Series queries.</p>
 *
 * <h3>Example</h3>
 * {@code SeriesQuery query = SeriesQuery.builder().name(".hack").build();}
 */
@Data
@Builder
public class SeriesQuery implements QueryParam {
    /** when given, performs a fuzzy search across series names and abbreviations */
    private String name;

    /** when given, performs an exact-match search for this abbreviation */
    private String abbreviation;

    /** moderator ID; when given, only series moderated by that user will be returned */
    private String moderator;

    @Override
    public Map<String, String> getQueryMap() {
        Map<String, String> queryMap = new HashMap<>();
        if (name != null) {
            queryMap.put("name", name);
        }
        if (abbreviation != null) {
            queryMap.put("abbreviation", abbreviation);
        }
        if (moderator != null) {
            queryMap.put("moderator", moderator);
        }
        return queryMap;
    }
}
