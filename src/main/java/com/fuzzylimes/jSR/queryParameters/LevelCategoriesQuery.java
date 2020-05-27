package com.fuzzylimes.jSR.queryParameters;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LevelCategoriesQuery {

    /** when given, filters (out) misc categories */
    private boolean miscellaneous;

}
