package com.fuzzylimes.jsr.query_parameters.sorting;

import com.fuzzylimes.jsr.query_parameters.QueryParam;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class Sorting<T extends OrderBy> implements QueryParam {

    private Direction direction;
    private T orderBy;

    @Override
    public Map<String, String> getQueryMap() {
        Map<String, String> queryMap = new HashMap<>();
        if (direction != null) {
            queryMap.put("direction", direction.getSortDirection());
        }
        if (orderBy != null) {
            queryMap.put("orderBy", orderBy.getOrderBy());
        }
        return queryMap;
    }
}
