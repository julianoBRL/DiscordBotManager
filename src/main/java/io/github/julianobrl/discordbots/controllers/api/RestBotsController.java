package io.github.julianobrl.discordbots.controllers.api;

import io.github.julianobrl.discordbots.entities.dtos.Bot;
import io.github.julianobrl.discordbots.integrations.socket.dto.reponses.SelfInfoResponse;
import io.github.julianobrl.discordbots.integrations.socket.dto.requests.SetActivityRequest;
import io.github.julianobrl.discordbots.services.BotService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/bots")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Bots", description = "Bots Manager")
public class RestBotsController {

    @Autowired
    private BotService service;

    @GetMapping("/list")
    public List<Bot> list(){
        return service.list();
    }

    @GetMapping("/view/{id}")
    public Bot view(@PathVariable(name = "id") String id){
        return service.getById(id);
    }

    @GetMapping("/info/{id}")
    public SelfInfoResponse info(@PathVariable(name = "id") String id){
        return service.getBotInfo(id);
    }

    @PostMapping("/add")
    public Bot create(@Valid @RequestBody Bot bot){
        return service.create(bot);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") String id) {
        service.delete(id);
    }

    @PostMapping("/start/{id}")
    public Bot start(@PathVariable(name = "id") String id) {
        return service.startBot(id);
    }

    @PostMapping("/stop/{id}")
    public Bot stop(@PathVariable(name = "id") String id) {
        return service.stopBot(id);
    }

    @PostMapping("/restart/{id}")
    public Bot restart(@PathVariable(name = "id") String id) {
        return service.restartBot(id);
    }

    @PostMapping("/activity/{id}")
    public String setActivity(@PathVariable(name = "id") String id,
                            @RequestBody SetActivityRequest activity) {
        return service.setActivity(id,activity);
    }

    @GetMapping("/status/{id}")
    public String getStatus(@PathVariable(name = "id") String id){
        return service.getBotStatus(id);
    }


}
