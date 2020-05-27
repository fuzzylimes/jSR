package com.fuzzylimes.jSR.resources;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Moderators {

    private Map<String, ModeratorRoles> moderatorRoles;

    private List<User> moderatorRolesEmbed;
}
