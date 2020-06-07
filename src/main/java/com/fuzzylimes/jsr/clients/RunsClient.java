package com.fuzzylimes.jsr.clients;

import com.fuzzylimes.jsr.sorting.Direction;
import com.fuzzylimes.jsr.sorting.RunsOrderBy;
import com.fuzzylimes.jsr.query_parameters.RunsQuery;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.resources.Run;

public class RunsClient {

    /**
     * GET runs
     *
     * Returns a {@link PagedResponse} of {@link Run}
     * Supports embedding of game,category,level,players,region,platform
     * Supports query parameters defined in {@link RunsQuery}
     * Order by and Direction in {@link RunsOrderBy} and {@link Direction}
     * https://github.com/speedruncomorg/api/blob/master/version1/runs.md#get-runs
     */

    /**
     * GET runs/{id}
     *
     * Returns a {@link Run} object for the queried id
     * Supports embedding of game,category,level,players,region,platform
     * https://github.com/speedruncomorg/api/blob/master/version1/runs.md#get-runsid
     */
}
