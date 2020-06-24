package com.fuzzylimes.jsr.query_parameters;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Builder to create the query parameters specific to User queries.</p>
 *
 * <h3>Example</h3>
 * {@code UserQuery query = UserQuery.builder().twitch("Elajjaz").build();}
 */
@Data
@Builder
public class UserQuery implements QueryParam {

    /**
     * when given, searches the value (case-insensitive exact-string match) across user names,
     * URLs and social profiles; all other query string filters are disabled when this is given
     */
    private String lookup;

    /**
     * only returns users whose name/URL contains the given value; the comparision is case-insensitive
     */
    private String name;

    /**
     * searches for Twitch usernames
     */
    private String twitch;

    /**
     * searches for Hitbox usernames
     */
    private String hitbox;

    /**
     * searches for Twitter usernames
     */
    private String twitter;

    /**
     * searches for SpeedRunsLive usernames
     */
    private String speedRunsLive;


    @Override
    public Map<String, String> getQueryMap() {
        Map<String, String> queryMap = new HashMap<>();
        if (lookup != null) {
            queryMap.put("lookup", lookup);
        }
        if (name != null) {
            queryMap.put("name", name);
        }
        if (twitch != null) {
            queryMap.put("twitch", twitch);
        }
        if (hitbox != null) {
            queryMap.put("hitbox", hitbox);
        }
        if (twitter != null) {
            queryMap.put("twitter", twitter);
        }
        if (speedRunsLive != null) {
            queryMap.put("speedrunslive", speedRunsLive);
        }
        return queryMap;
    }
}
