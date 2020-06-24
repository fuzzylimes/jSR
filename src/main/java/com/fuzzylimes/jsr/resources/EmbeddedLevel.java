package com.fuzzylimes.jsr.resources;

import lombok.Data;

/**
 * Container for deserializing a Level inside another resource
 */
@Data
public class EmbeddedLevel {
    /** representation as object when embedding enabled */
    private Level levelEmbed;

    /** representation as id */
    private String id;

}