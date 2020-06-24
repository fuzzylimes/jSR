package com.fuzzylimes.jsr.query_parameters;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Builder to create the query parameters specific to performing Game queries.</p>
 *
 * <h3>Example</h3>
 * {@code GamesQuery query = GamesQuery.builder().name("darksouls").romhack(false).build();}
 */
@Data
@Builder
public class GamesQuery implements QueryParam {

    /** when given, performs a fuzzy search across game names and abbreviations */
    private String name;
    /** when given, performs an exact-match search for this abbreviation */
    private String abbreviation;
    /** when given, restricts to games released in that year */
    private Integer released;
    /** game type ID; when given, restricts to that game type */
    private String gametype;
    /** platform ID; when given, restricts to that platform */
    private String platform;
    /** region ID; when given, restricts to that region */
    private String region;
    /** genre ID; when given, restricts to that genre */
    private String genre;
    /** engine ID; when given, restricts to that engine */
    private String engine;
    /** developer ID; when given, restricts to that developer */
    private String developer;
    /** publisher ID; when given, restricts to that publisher */
    private String publisher;
    /** moderator ID; when given, only games moderated by that user will be returned */
    private String moderator;
    /** legacy parameter, do not use this in new code; whether or not to include games with game types (if this parameter is not set, game types are included; if it is set to a true value, only games with game types will be returned, otherwise only games without game types are returned) */
    private Boolean romhack;
    /** enable bulk access */
    private Boolean bulk;
    /** max number of responses per page when using bulk query; defaults to 250 */
    private Integer max;

    @Override
    public Map<String, String> getQueryMap() {
        Map<String, String> queryMap = new HashMap<>();
        if(name != null) {
            queryMap.put("name", name);
        }
        if(abbreviation != null) {
            queryMap.put("abbreviation", abbreviation);
        }
        if(released != null) {
            queryMap.put("released", released.toString());
        }
        if(gametype != null) {
            queryMap.put("gametype", gametype);
        }
        if(platform != null) {
            queryMap.put("platform", platform);
        }
        if(region != null) {
            queryMap.put("region", region);
        }
        if(genre != null) {
            queryMap.put("genre", genre);
        }
        if(engine != null) {
            queryMap.put("engine", engine);
        }
        if(developer != null) {
            queryMap.put("developer", developer);
        }
        if(publisher != null) {
            queryMap.put("publisher", publisher);
        }
        if(moderator != null) {
            queryMap.put("moderator", moderator);
        }
        if(romhack != null) {
            queryMap.put("romhack", romhack.toString());
        }
        if(bulk != null) {
            queryMap.put("_bulk", bulk.toString());
        }
        if(max != null) {
            queryMap.put("max", max.toString());
        }
        return queryMap;
    }
}
