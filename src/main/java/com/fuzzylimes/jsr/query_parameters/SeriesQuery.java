package com.fuzzylimes.jsr.query_parameters;

import lombok.Data;

@Data
public class SeriesQuery {
    /** when given, performs a fuzzy search across series names and abbreviations */
    private String name;

    /** when given, performs an exact-match search for this abbreviation */
    private String abbreviation;

    /** moderator ID; when given, only series moderated by that user will be returned */
    private String moderator;
}
