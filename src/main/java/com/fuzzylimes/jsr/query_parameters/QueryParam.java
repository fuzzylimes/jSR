package com.fuzzylimes.jsr.query_parameters;

import java.util.Map;

/**
 * Implemented by all Query objects
 */
public interface QueryParam {

    /**
     * @return A map of all query parameters
     */
    Map<String, String> getQueryMap();
}
