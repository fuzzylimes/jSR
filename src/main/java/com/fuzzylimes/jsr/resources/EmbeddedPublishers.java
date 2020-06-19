package com.fuzzylimes.jsr.resources;

import lombok.Data;

import java.util.List;

@Data
public class EmbeddedPublishers {
    private List<String> ids;
    private List<Publisher> embedded;
}
