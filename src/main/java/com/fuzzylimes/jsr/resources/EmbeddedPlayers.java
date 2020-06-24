package com.fuzzylimes.jsr.resources;

import lombok.Data;

import java.util.List;

/**
 * Container for deserializing Players inside another resource
 */
@Data
public class EmbeddedPlayers {

    /** grouping of User and guest Objects */
    private SpeedRunUsers playersEmbed;

    /** representation as a list of abbreviated player objects */
    private List<PlayersItem> players;

}