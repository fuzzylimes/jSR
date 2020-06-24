package com.fuzzylimes.jsr.query_parameters.sorting;

import com.fuzzylimes.jsr.query_parameters.QueryParam;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Builder object that contains all sorting information for a query. If you want to change how the data is sorted, or
 * the direction in which it is returned, you will use this generic builds to construct the Sorting object before
 * passing it to its corresponding query method.</p>
 *
 * <h3>Example</h3>
 * {@code Sorting<RunsOrderBy> order = Sorting.<RunsOrderBy>builder().direction(Direction.ASCCENDING).orderBy(RunsOrderBy.GAME).build();}
 *
 * @param <T> The "OrderBy" resource type to be constructed
 */
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
