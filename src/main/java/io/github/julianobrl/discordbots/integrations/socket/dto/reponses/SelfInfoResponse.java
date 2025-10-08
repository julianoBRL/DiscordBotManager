package io.github.julianobrl.discordbots.integrations.socket.dto.reponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dv8tion.jda.api.entities.SelfUser;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelfInfoResponse {
    private OffsetDateTime createdAt;
    private String name;
    private String avatarUrl;
    private boolean verified;
    private boolean bot;
    private boolean system;
}
