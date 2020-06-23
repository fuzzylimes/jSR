package com.fuzzylimes.jsr.resources;

import lombok.Data;

import java.util.List;

@Data
public class EmbeddedPlatforms {
    private List<String> ids;
    private List<Platform> embedded;
}
