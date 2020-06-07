package com.fuzzylimes.jsr.query_parameters;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class CategoryRecordsQuery implements QueryParam {

    /** only return the top N places (this can result in more than N runs!); this is set to 3 by default */
    private Integer top;

    /** when set to a true value, empty leaderboards will not show up in the result */
    private Boolean skipEmpty;

    @Override
    public Map<String, String> getQueryMap() {
        Map<String, String> queryMap = new HashMap<>();
        if (top != null) {
            queryMap.put("top", top.toString());
        }
        if(skipEmpty != null) {
            queryMap.put("skip-empty", skipEmpty.toString());
        }
        return queryMap;
    }
}
