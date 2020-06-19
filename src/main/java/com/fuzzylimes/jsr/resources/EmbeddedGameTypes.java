package com.fuzzylimes.jsr.resources;

import lombok.Data;

import java.util.List;

@Data
public class EmbeddedGameTypes {
    private List<String> ids;
    private List<GameType> embedded;
}
