package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserRoles {

    @JsonProperty("banned")
    BANNED("banned"),
    @JsonProperty("user")
    USER("user"),
    @JsonProperty("trusted")
    TRUSTED("trusted"),
    @JsonProperty("moderator")
    MODERATOR("moderator"),
    @JsonProperty("admin")
    ADMIN("admin"),
    @JsonProperty("programmer")
    PROGRAMMER("programmer");

    private String role;

    private UserRoles(String role) {
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
