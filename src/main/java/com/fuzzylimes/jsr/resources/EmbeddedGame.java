package com.fuzzylimes.jsr.resources;

import lombok.Data;

/**
 * Container for deserializing a Game inside another resource
 */
@Data
public class EmbeddedGame {

    /** representation as object when embedding enabled */
    private Game gameEmbed;

    /** representation as id */
    private String id;

}
