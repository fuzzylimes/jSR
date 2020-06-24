package com.fuzzylimes.jsr.resources;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * People that evaluate and approve submitted runs for a game
 */
@Data
public class Moderators {

    /** mapping of ids to roles */
    private Map<String, ModeratorRoles> moderatorRoles;

    /**
     * list of users that are moderators; included when embedding is enabled.
     * Note - moderator role will not be present in response.
     */
    private List<User> moderatorRolesEmbed;
}
