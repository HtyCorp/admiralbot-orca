package com.admiralbot.orca.model.discord.channel;

import com.admiralbot.orca.model.discord.Snowflake;
import com.admiralbot.orca.model.discord.StringBitfield;
import com.admiralbot.orca.model.discord.User;
import com.admiralbot.orca.model.discord.guild.Permission;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public record Channel(
        @JsonProperty(value = "id", required = true) Snowflake id,
        @JsonProperty(value = "type", required = true) ChannelType type,
        @JsonProperty("guild_id") Snowflake guildId,
        @JsonProperty("position") Integer sortingPosition,
        @JsonProperty("permission_overwrites")List<PermissionOverwrite> permissionOverwrites,
        @JsonProperty("name") String name,
        @JsonProperty("topic") String topic,
        @JsonProperty("nsfw") Boolean isNsfw,
        @JsonProperty("last_message_id") Snowflake lastMessageId,
        @JsonProperty("bitrate") Integer bitrate,
        @JsonProperty("user_limit") Integer userLimit,
        @JsonProperty("rate_limit_per_user") Integer userMessageCooldownSeconds,
        @JsonProperty("recipients") List<User> dmRecipients,
        @JsonProperty("icon") String iconHash,
        @JsonProperty("owner_id") Snowflake dmOrThreadOwnerId,
        @JsonProperty("application_id") Snowflake dmOrThreadOwnerAppId,
        @JsonProperty("managed") Boolean isManagedGroupDm,
        @JsonProperty("parent_id") Snowflake parentId,
        @JsonProperty("last_pin_timestamp") Instant lastPinTime,
        @JsonProperty("rtc_region") String voiceRegionId,
        @JsonProperty("video_quality_mode") Integer videoQualityMode,
        @JsonProperty("message_count") Long threadMessageCount,
        @JsonProperty("member_count") Integer threadMemberCount,
        @JsonProperty("thread_metadata") ThreadMetadata threadMetadata,
        @JsonProperty("member") ThreadMember threadMember,
        @JsonProperty("default_auto_archive_duration") Integer defaultAutoArchiveDurationMinutes,
        @JsonProperty("permissions") StringBitfield<Permission> slashCommandInteractionPermissions,
        @JsonProperty("flags") Long flags, // No wrapper: not currently useful
        @JsonProperty("total_message_sent") Long threadTotalMessageSent,
        @JsonProperty("available_tags") List<ForumTag> availableForumTags,
        @JsonProperty("applied_tags") List<Snowflake> appliedForumTagIds,
        @JsonProperty("default_reaction_emoji") DefaultReaction defaultReactionEmoji,
        @JsonProperty("default_thread_rate_limit_per_user") Integer defaultUserMessageCooldownSeconds,
        @JsonProperty("default_sort_order") Integer defaultSortOrderType,
        @JsonProperty("default_forum_layout") Integer defaultForumLayoutType
) {}
