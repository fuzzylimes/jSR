package com.fuzzylimes.jsr.resources;

import lombok.Data;

/**
 * Container for deserializing a Category inside another resource
 */
@Data
public class EmbeddedCategory {

    /** representation as object when embedding enabled */
    private Category categoryEmbed;

    /** representation as id */
    private String id;

}