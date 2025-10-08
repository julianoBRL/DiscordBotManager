package io.github.julianobrl.discordbots.controllers.api;

import io.github.julianobrl.discordbots.integrations.socket.dto.reponses.AllGuildsResponse;
import io.github.julianobrl.discordbots.integrations.socket.dto.reponses.GuildResponse;
import io.github.julianobrl.discordbots.integrations.socket.dto.reponses.GuildsResponse;
import io.github.julianobrl.discordbots.services.GuildService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guilds")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Guilds", description = "Guilds Manager")
public class RestGuildsController {

    private final GuildService service;

    @GetMapping
    protected List<AllGuildsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/bot/{botId}/list")
    protected List<GuildsResponse> getAllFromBot(@PathVariable(name = "botId") String botId){
        return service.getAllFromBot(botId);
    }

    @GetMapping("/bot/{botId}/view/{guildId}")
    protected GuildResponse getView(
            @PathVariable(name = "botId") String botId,
            @PathVariable(name = "guildId") String guildId
    ){
        return service.getById(botId, guildId);
    }

}
