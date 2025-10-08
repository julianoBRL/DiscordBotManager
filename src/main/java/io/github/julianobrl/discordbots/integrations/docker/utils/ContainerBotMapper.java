package io.github.julianobrl.discordbots.integrations.docker.utils;

import com.github.dockerjava.api.model.Container;
import io.github.julianobrl.discordbots.entities.dtos.Bot;
import io.github.julianobrl.discordbots.entities.enums.BotStatus;

import java.time.Instant;
import java.time.ZoneId;

public class ContainerBotMapper {

    public static Bot map(Container container){
        return Bot.builder()
                    .id(container.getLabels().get("bot-id"))
                    .prefix(container.getLabels().get("bot-prefix"))
                    .name(container.getLabels().get("bot-name"))
                    .status(BotStatus.fromDockerState(container.getState()))
                    .containerId(container.getId())
                    .containerName(container.getNames()[0].replaceFirst("/", ""))
                    .createdAt(Instant.ofEpochSecond(container.getCreated())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime())
                .build();

    }

}
