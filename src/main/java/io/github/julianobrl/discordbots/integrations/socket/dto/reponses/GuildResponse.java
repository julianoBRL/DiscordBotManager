package io.github.julianobrl.discordbots.integrations.socket.dto.reponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.DiscordLocale;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuildResponse {
    private String id;
    private String name;
    private String iconUrl;
    private String bannerUrl;
    private String vanityUrl;
    private String splashUrl;
    private String description;
    private GuildOwnerResponse owner;
    private Guild.NSFWLevel nsfwLevel;
    private Guild.BoostTier boostTier;
    private int boostCount;
    private int maxEmojis;
    private long maxFileSize;
    private int memberCount;
    private int maxMembers;
    private boolean invitesDisabled;
    private DiscordLocale locale;
}
