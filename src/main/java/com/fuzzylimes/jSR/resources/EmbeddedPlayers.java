package com.fuzzylimes.jSR.resources;

import lombok.Data;

import java.util.List;

@Data
public class EmbeddedPlayers {

    private SpeedRunUsers playersEmbed;

    private List<PlayersItem> players;

}