package com.admiralbot.orca.discord.model.interaction;

import com.admiralbot.orca.discord.model.components.ButtonStyle;
import com.admiralbot.orca.discord.model.components.MessageComponentType;
import com.admiralbot.orca.discord.model.components.ActionRowComponent;
import com.admiralbot.orca.discord.model.components.ButtonComponent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InteractionParseTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
    }

    private static final String PING_INTERACTION = """
            {
                "version": 1,
                "type": 1,
                "token": "unique_interaction_token",
                "id": "846462639134605312",
                "application_id": "290926444748734465"
            }
            """;

    @Test
    public void testPingInteractionParseSucceeds() throws JsonProcessingException {
        var interaction = OBJECT_MAPPER.readValue(PING_INTERACTION, Interaction.class);
        assertEquals(InteractionType.PING, interaction.type());
        assertNull(interaction.data());
    }

    // Event is slightly modified from documented example, since it's missing some required fields per API docs (huh?)
    private static final String SAMPLE_COMPONENT_INTERACTION = """
            {
                "version": 1,
                "type": 3,
                "token": "unique_interaction_token",
                "message": {
                    "type": 0,
                    "tts": false,
                    "timestamp": "2021-05-19T02:12:51.710000+00:00",
                    "pinned": false,
                    "mentions": [],
                    "mention_roles": [],
                    "mention_everyone": false,
                    "id": "844397162624450620",
                    "flags": 0,
                    "embeds": [],
                    "edited_timestamp": null,
                    "content": "This is a message with components.",
                    "components": [
                        {
                            "type": 1,
                            "components": [
                                {
                                    "type": 2,
                                    "label": "Click me!",
                                    "style": 1,
                                    "custom_id": "click_one"
                                }
                            ]
                        }
                    ],
                    "channel_id": "345626669114982402",
                    "author": {
                        "username": "Mason",
                        "public_flags": 131141,
                        "id": "53908232506183680",
                        "discriminator": "1337",
                        "avatar": "a_d5efa99b3eeaa7dd43acca82f5692432"
                    },
                    "attachments": []
                },
                "member": {
                    "user": {
                        "username": "Mason",
                        "public_flags": 131141,
                        "id": "53908232506183680",
                        "discriminator": "1337",
                        "avatar": "a_d5efa99b3eeaa7dd43acca82f5692432"
                    },
                    "roles": [
                        "290926798626357999"
                    ],
                    "premium_since": null,
                    "permissions": "17179869183",
                    "pending": false,
                    "nick": null,
                    "mute": false,
                    "joined_at": "2017-03-13T19:19:14.040000+00:00",
                    "flags": 0,
                    "pending": false,
                    "deaf": false,
                    "avatar": null
                },
                "id": "846462639134605312",
                "guild_id": "290926798626357999",
                "data": {
                    "custom_id": "click_one",
                    "component_type": 2
                },
                "channel_id": "345626669114982999",
                "application_id": "290926444748734465"
            }
            """;

    @Test
    public void testButtonComponentInteractionHasValidValues() throws JsonProcessingException {
        var interaction = OBJECT_MAPPER.readValue(SAMPLE_COMPONENT_INTERACTION, Interaction.class);

        var messageComponentData = (MessageComponentData) interaction.data();
        assertEquals(messageComponentData.componentType(), MessageComponentType.BUTTON);

        var componentsInMessage = interaction.message().components();

        var rowComponent = (ActionRowComponent) componentsInMessage.get(0);
        assertEquals(rowComponent.type(), MessageComponentType.ACTION_ROW);

        var buttonComponent = (ButtonComponent) rowComponent.components().get(0);
        assertEquals(buttonComponent.type(), MessageComponentType.BUTTON);
        assertEquals("Click me!", buttonComponent.label());
        assertEquals("click_one", buttonComponent.customId());
        assertEquals(ButtonStyle.PRIMARY, buttonComponent.style());
    }

}
