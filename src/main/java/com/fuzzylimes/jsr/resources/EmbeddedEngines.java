package com.fuzzylimes.jsr.resources;

import lombok.Data;

import java.util.List;

@Data
public class EmbeddedEngines {
    private List<String> ids;
    private List<Engine> embedded;
}
