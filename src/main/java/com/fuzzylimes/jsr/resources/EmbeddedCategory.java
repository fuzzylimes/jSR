package com.fuzzylimes.jsr.resources;

import lombok.Data;

@Data
public class EmbeddedCategory {

    private Category categoryEmbed;

    private String id;

}