package io.github.julianobrl.discordbots.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.julianobrl.discordbots.entities.dtos.Bot;
import io.github.julianobrl.discordbots.integrations.docker.services.DockerService;
import io.github.julianobrl.discordbots.factories.BotFactory;
import io.github.julianobrl.discordbots.integrations.socket.components.SocketMessenger;
import io.github.julianobrl.discordbots.integrations.docker.utils.ContainerBotMapper;
import io.github.julianobrl.discordbots.integrations.socket.dto.reponses.SelfInfoResponse;
import io.github.julianobrl.discordbots.integrations.socket.dto.requests.CommandRequest;
import io.github.julianobrl.discordbots.integrations.socket.dto.requests.SetActivityRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotService {

    private final DockerService dockerService;
    private final BotFactory botFactory;
    private final PluginService pluginService;
    private final SocketMessenger messenger;
    private final ObjectMapper mapper;

    public List<Bot> list() {
        return dockerService.listContainers().stream()
                .map(ContainerBotMapper::map).toList();
    }

    public Bot create(Bot bot) {
        return botFactory.create(bot);
    }

    public Bot getById(String id) {
        return ContainerBotMapper.map(dockerService.getContainerById(id));
    }

    public void delete(String botId) {
        pluginService.getInstalledPluginsByBotId(botId).forEach((plugin) ->{
            pluginService.uninstall(plugin.getId(), botId);
        });
        botFactory.delete(ContainerBotMapper.map(dockerService.getContainerById(botId)));
    }

    public Bot restartBot(String id){
        return ContainerBotMapper.map(dockerService.restartContainerById(id));
    }

    public Bot stopBot(String id){
        return ContainerBotMapper.map(dockerService.stopContainer(id));
    }

    public Bot startBot(String id){
        return ContainerBotMapper.map(dockerService.startContainer(id));
    }

    public SelfInfoResponse getBotInfo(String id){
        return messenger.sendMessage(id, new CommandRequest("SELF", null), SelfInfoResponse.class);
    }

    public String setActivity(String id, SetActivityRequest activity) {
        return messenger.sendMessage(id,new CommandRequest("SET_ACTIVITY",activity), String.class);
    }

    public String getBotStatus(String id) {
        return messenger.sendMessage(id,new CommandRequest("GET_STATUS",null), String.class);
    }
}
