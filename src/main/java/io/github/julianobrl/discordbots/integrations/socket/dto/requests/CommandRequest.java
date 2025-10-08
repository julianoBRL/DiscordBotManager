package io.github.julianobrl.discordbots.integrations.socket.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandRequest {
    private String command;
    private Object data;
}
