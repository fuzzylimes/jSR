package com.fuzzylimes.jsr.resources;

import lombok.Data;

import java.util.List;

/**
 * Container for deserializing Platforms inside another resource
 */
@Data
public class EmbeddedPlatforms {
    /** representation as a list of ids */
    private List<String> ids;
    /** representation as list of objects when embedding enabled */
    private List<Platform> embedded;
}
