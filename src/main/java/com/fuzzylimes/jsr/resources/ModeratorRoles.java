package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Roles that a moderator can have for a given game
 */
public enum ModeratorRoles {

    @JsonProperty("moderator")
    MODERATOR("moderator"),
    @JsonProperty("super-moderator")
    SUPER_MODERATOR("super-moderator");

    private String role;

    ModeratorRoles(String role) {
        this.role = role;
    }

    @JsonProperty
    public String getRole() {
        return role;
    }
}
