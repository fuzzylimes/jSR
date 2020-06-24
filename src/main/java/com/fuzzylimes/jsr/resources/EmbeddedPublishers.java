package com.fuzzylimes.jsr.resources;

import lombok.Data;

import java.util.List;

/**
 * Container for deserializing Publishers inside another resource
 */
@Data
public class EmbeddedPublishers {
    /** representation as a list of ids */
    private List<String> ids;

    /** representation as list of objects when embedding enabled */
    private List<Publisher> embedded;
}
