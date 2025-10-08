package io.github.julianobrl.discordbots.controllers.api;

import io.github.julianobrl.discordbots.entities.Plugin;
import io.github.julianobrl.discordbots.services.PluginService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plugins")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Plugins", description = "Plugins Manager")
public class RestPluginsController {

    private final PluginService pluginService;

    @GetMapping("/list")
    protected List<Plugin> get(){
        return pluginService.list();
    }

    @PostMapping("/add")
    protected Plugin addPlugin(@RequestParam(name = "url") String url) {
        return pluginService.createByUrl(url);
    }

    @GetMapping("/{id}/view")
    protected Plugin getView(@PathVariable(name = "id") Long id){
        return pluginService.getById(id);
    }

    @DeleteMapping("/{id}/delete")
    protected void delete(@PathVariable(name = "id") Long id){
        pluginService.delete(id);
    }

    @PostMapping("/install")
    protected Plugin installPlugin(@RequestParam(name = "pluginId") Long pluginId,
                                   @RequestParam(name = "botId") String botId,
                                   @RequestParam(name = "version") String version) {
        return pluginService.install(pluginId, botId, version);
    }

    @DeleteMapping("/uninstall")
    protected Plugin uninstallPlugin(@RequestParam(name = "pluginId") Long pluginId,
                                     @RequestParam(name = "botId") String botId) {
        return pluginService.uninstall(pluginId, botId);
    }

    @GetMapping("/installed/bot/{id}")
    protected List<Plugin> installedInBot(@PathVariable(name = "id") String botId){
        return pluginService.getInstalledPluginsByBotId(botId);
    }

}
