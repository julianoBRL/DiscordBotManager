package io.github.julianobrl.discordbots.integrations.socket.dto.reponses;

import io.github.julianobrl.discordbots.entities.dtos.Bot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllGuildsResponse {
    private Bot bot;
    private List<GuildsResponse> guilds;
}
