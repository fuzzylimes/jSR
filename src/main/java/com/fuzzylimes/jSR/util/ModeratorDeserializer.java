package com.fuzzylimes.jSR.util;//package com.fuzzylimes.jSR.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fuzzylimes.jSR.resources.ModeratorRoles;
import com.fuzzylimes.jSR.resources.Moderators;
import com.fuzzylimes.jSR.resources.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModeratorDeserializer extends JsonDeserializer {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public Moderators deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);
        return buildModerators(root);
    }

    private Moderators buildModerators(JsonNode root) throws JsonProcessingException {
        Moderators moderators = new Moderators();

        if (!root.has("data")) {
            MapType moderatorRoleType = mapper.getTypeFactory().constructMapType(HashMap.class, String.class, ModeratorRoles.class);
            Map<String, ModeratorRoles> roles = mapper.readValue(root.toString(), moderatorRoleType);
            moderators.setModeratorRoles(roles);
        } else {
            CollectionType listOfModerators = mapper.getTypeFactory().constructCollectionType(List.class, User.class);
            List<User> val = mapper.readValue(root.get("data").toString(), listOfModerators);
            moderators.setModeratorRolesEmbed(val);
        }

        return moderators;
    }
}
