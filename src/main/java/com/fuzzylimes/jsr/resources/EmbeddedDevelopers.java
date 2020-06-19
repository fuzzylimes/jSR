package com.fuzzylimes.jsr.resources;

import lombok.Data;

import java.util.List;

@Data
public class EmbeddedDevelopers {
    private List<String> ids;
    private List<Developer> embedded;
}
