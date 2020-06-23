package com.fuzzylimes.jsr.resources;

import lombok.Data;

import java.util.List;

@Data
public class EmbeddedLevels {
    private List<String> ids;
    private List<Level> embedded;
}
