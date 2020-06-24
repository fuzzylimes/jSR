package com.fuzzylimes.jsr.query_parameters;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Builder to create the query parameters specific to performing level/{id}/categories queries.</p>
 *
 * <h3>Example</h3>
 * {@code LevelCategoriesQuery query = LevelCategoriesQuery.builder().miscellaneous(false).build();}
 */
@Data
@Builder
public class LevelCategoriesQuery implements QueryParam {

    /** when given, filters (out) misc categories */
    private Boolean miscellaneous;

    @Override
    public Map<String, String> getQueryMap() {
        Map<String, String> queryMap = new HashMap<>();
        if (miscellaneous != null) {
            queryMap.put("miscellaneous", miscellaneous.toString());
        }
        return queryMap;
    }

}
