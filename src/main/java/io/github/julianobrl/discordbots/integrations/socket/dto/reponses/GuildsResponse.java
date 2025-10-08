package io.github.julianobrl.discordbots.integrations.socket.dto.reponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dv8tion.jda.api.entities.Guild;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuildsResponse {
    private String name;
    private String iconUrl;
    private String bannerUrl;
    private String id;
}
