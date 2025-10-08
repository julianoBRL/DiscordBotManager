package io.github.julianobrl.discordbots.integrations.socket.dto.reponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dv8tion.jda.api.entities.Member;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuildOwnerResponse {
    private String name;
    private String avatarUrl;
    private String nickname;
    private boolean owner;
    private boolean boosting;
}
