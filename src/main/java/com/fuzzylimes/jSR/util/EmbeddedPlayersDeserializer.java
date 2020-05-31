package com.fuzzylimes.jSR.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fuzzylimes.jSR.resources.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmbeddedPlayersDeserializer extends JsonDeserializer {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public EmbeddedPlayers deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);
        return buildPlayers(root);
    }

    private EmbeddedPlayers buildPlayers(JsonNode root) throws JsonProcessingException {
        EmbeddedPlayers embeddedPlayers = new EmbeddedPlayers();

        if (!root.has("data")) {
            buildSimplePlayer(root, embeddedPlayers);
        } else {
            List<User> userList = new ArrayList<>();
            List<Guest> guestList = new ArrayList<>();
            SpeedRunUsers speedRunUsers = new SpeedRunUsers(userList, guestList);
            embeddedPlayers.setPlayersEmbed(speedRunUsers);
            for (JsonNode data : root.get("data")) {
                if (data.get("rel").toString().equals("\"guest\"")) {
                    guestList.add(mapper.readValue(data.toString(), Guest.class));
                } else {
                    userList.add(mapper.readValue(data.toString(), User.class));
                }
            }
        }
        return embeddedPlayers;
    }

    private void buildSimplePlayer(JsonNode root, EmbeddedPlayers embeddedPlayers) throws JsonProcessingException {
        CollectionType listOfPlayer = mapper.getTypeFactory().constructCollectionType(List.class, PlayersItem.class);
        List<PlayersItem> playersItemList = mapper.readValue(root.toString(), listOfPlayer);
        embeddedPlayers.setPlayers(playersItemList);
    }
}
