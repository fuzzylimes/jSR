package com.fuzzylimes.jsr.query_parameters;

import java.util.HashMap;
import java.util.Map;

public class QueryParamUtil {

    private QueryParamUtil() {
        // utill class
    }

    public Map<String,String> buildQueryParams(Map<String,String>... args) {
        Map<String, String> queryMap = new HashMap<>();
        for (Map<String, String> arg : args) {
            for (Map.Entry<String, String> param : arg.entrySet()) {
                queryMap.put(param.getKey(), param.getValue());
            }
        }
        return queryMap;
    }

}
