package io.github.julianobrl.discordbots.integrations.socket.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dv8tion.jda.api.entities.Activity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetActivityRequest {
    private Activity.ActivityType type;
    private String name;
}
