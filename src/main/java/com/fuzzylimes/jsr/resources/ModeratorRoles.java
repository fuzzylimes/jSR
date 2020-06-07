package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ModeratorRoles {

    @JsonProperty("moderator")
    MODERATOR("moderator"),
    @JsonProperty("super-moderator")
    SUPER_MODERATOR("super-moderator");

    private String role;

    private ModeratorRoles(String role) {
        this.role = role;
    }

    @JsonProperty
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
