package com.fuzzylimes.jsr.resources;

import lombok.Data;

import java.util.List;

@Data
public class EmbeddedGenres {
    private List<String> ids;
    private List<Genre> embedded;
}
