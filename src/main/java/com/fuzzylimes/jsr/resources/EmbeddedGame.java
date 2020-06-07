package com.fuzzylimes.jsr.resources;

import lombok.Data;

@Data
public class EmbeddedGame {

    private Game gameEmbed;

    private String id;

}
