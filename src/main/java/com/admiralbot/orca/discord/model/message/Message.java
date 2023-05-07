package com.admiralbot.orca.discord.model.message;

import com.admiralbot.orca.discord.model.NumberBitfield;
import com.admiralbot.orca.discord.model.Snowflake;
import com.admiralbot.orca.discord.model.User;
import com.admiralbot.orca.discord.model.channel.Channel;
import com.admiralbot.orca.discord.model.channel.ChannelMention;
import com.admiralbot.orca.discord.model.components.MessageComponent;
import com.admiralbot.orca.discord.model.embed.Embed;
import com.admiralbot.orca.discord.model.interaction.MessageInteraction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Message(
        @JsonProperty(value = "id", required = true) Snowflake id,
        @JsonProperty(value = "channel_id", required = true) Snowflake channelId,
        // This gets funky if it's a webhook message. Keep an eye out...
        @JsonProperty(value = "author", required = true) User author,
        @JsonProperty(value = "content", required = true) String content,
        @JsonProperty(value = "timestamp", required = true) Instant time,
        @JsonProperty("edited_timestamp") Instant editedTime,
        @JsonProperty(value = "tts", required = true) boolean isTts,
        @JsonProperty(value = "mention_everyone", required = true) boolean isMentionEveryone,
        @JsonProperty(value = "mentions", required = true) List<User> userMentions,
        @JsonProperty(value = "mention_roles", required = true) List<Snowflake> roleIdMentions,
        @JsonProperty("mention_channels") List<ChannelMention> channelMentions,
        @JsonProperty(value = "attachments", required = true) List<Attachment> attachments,
        @JsonProperty(value = "embeds", required = true) List<Embed> embeds,
        @JsonProperty("reactions") List<Reaction> reactions,
        // Skipping "nonce" since it is (int|string) and not needed right now
        @JsonProperty(value = "pinned", required = true) boolean isPinned,
        @JsonProperty("webhook_id") Snowflake senderWebhookId,
        @JsonProperty(value = "type", required = true) MessageType type,
        // Skipping `activity`, `presence`: related to Rich Presence, not useful.
        @JsonProperty("message_reference") MessageReference messageReference,
        @JsonProperty("flags") NumberBitfield<MessageFlag> flags,
        @JsonProperty("referenced_message") Message referencedMessage,
        @JsonProperty("interaction") MessageInteraction interaction,
        @JsonProperty("thread") Channel thread,
        @JsonProperty("components") List<MessageComponent> components,
        // Skipping `sticker_items`, `stickers`: not relevant.
        @JsonProperty("position") Long threadPosition
        // Skipping `role_subscription_data`: role subscriptions don't seem relevant.
) {}
