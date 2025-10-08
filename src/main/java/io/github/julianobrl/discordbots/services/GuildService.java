package io.github.julianobrl.discordbots.services;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.julianobrl.discordbots.integrations.socket.dto.reponses.AllGuildsResponse;
import io.github.julianobrl.discordbots.entities.dtos.Bot;
import io.github.julianobrl.discordbots.exceptions.BotException;
import io.github.julianobrl.discordbots.integrations.docker.services.DockerService;
import io.github.julianobrl.discordbots.integrations.docker.utils.ContainerBotMapper;
import io.github.julianobrl.discordbots.integrations.socket.components.SocketMessenger;
import io.github.julianobrl.discordbots.integrations.socket.dto.reponses.GuildResponse;
import io.github.julianobrl.discordbots.integrations.socket.dto.reponses.GuildsResponse;
import io.github.julianobrl.discordbots.integrations.socket.dto.requests.CommandRequest;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuildService {

    private final SocketMessenger messenger;
    private final DockerService dockerService;

    public List<GuildsResponse> getAllFromBot(String botId){
        TypeReference<List<GuildsResponse>> typeReference = new TypeReference<>() {};
        return messenger.sendMessage(botId, new CommandRequest("GET_GUILDS", null), typeReference);
    }

    public GuildResponse getById(String botId, String guildId) {
        return messenger.sendMessage(botId, new CommandRequest("GET_GUILD", guildId), GuildResponse.class);
    }

    public List<AllGuildsResponse> getAll() {
        List<AllGuildsResponse> allGuildResponses = new ArrayList<>();
        dockerService.listContainers().forEach((container -> {
            Bot bot = ContainerBotMapper.map(container);
            try {
                TypeReference<List<GuildsResponse>> typeReference = new TypeReference<>() {
                };
                List<GuildsResponse> guilds = messenger.sendMessage(bot.getId(), new CommandRequest("GET_GUILDS", null), typeReference);
                allGuildResponses.add(new AllGuildsResponse(bot, guilds));
            }catch (BotException be){
                allGuildResponses.add(new AllGuildsResponse(bot, null));
            }
        }));
        return allGuildResponses;
    }
}
