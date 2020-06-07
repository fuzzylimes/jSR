package com.fuzzylimes.jsr.query_parameters;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPersonalBestsQuery {

    /** when given, only PBs with a place equal or better than this value (e.g. top=1 returns all World Records of the given user) */
    private int top;

    /** when given, restricts the result to games and romhacks in that series; can be either a series ID or abbreviation */
    private String series;

    /** when given, restricts the result to that game; can be either a game ID or abbreviation */
    private String game;

}
