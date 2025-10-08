package io.github.julianobrl.discordbots.integrations.socket.components;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.julianobrl.discordbots.configs.BotDeployConfigs;
import io.github.julianobrl.discordbots.exceptions.BotException;
import io.github.julianobrl.discordbots.integrations.socket.dto.requests.CommandRequest;
import io.github.julianobrl.discordbots.integrations.socket.exceptions.SocketException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
@RequiredArgsConstructor
public class SocketMessenger {

    private final ObjectMapper mapper;
    private final BotDeployConfigs deployConfigs;

    private String sendAndReceive(String botId, CommandRequest cmd) throws Exception {
        UnixDomainSocketAddress address = UnixDomainSocketAddress.of(socketPathBuilder(botId));

        log.info("Connecting to the socket...");
        try (SocketChannel clientChannel = SocketChannel.open(address)) {
            log.info("Connected to the socket...");

            // 1. Send the command
            ByteBuffer buffer = ByteBuffer.wrap(mapper.writeValueAsString(cmd).getBytes());
            clientChannel.write(buffer);
            log.info("Message sent...");

            // 2. Wait for and read the response
            log.info("Waiting for response...");
            ByteBuffer responseBuffer = ByteBuffer.allocate(1024); // Allocate a reasonable buffer size
            int bytesRead = clientChannel.read(responseBuffer);

            if (bytesRead > 0) {
                String response = new String(responseBuffer.array(), 0, bytesRead).trim();
                log.info("Response received!");
                return response;
            }

            log.info("No response received");
            return null;

        }
    }

    public <T> T sendMessage(String botId, CommandRequest cmd, Class<T> responseClass) {
        try {
            String response = sendAndReceive(botId, cmd);
            if (response != null) {
                return mapper.readValue(response, responseClass);
            }
            return null;

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BotException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public <T> T sendMessage(String botId, CommandRequest cmd, TypeReference<T> typeReference) {
        try {
            String response = sendAndReceive(botId, cmd);
            if (response != null) {
                return mapper.readValue(response, typeReference);
            }
            return null;

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BotException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public Path socketPathBuilder(String botId) throws SocketException {
        String deployName = deployConfigs.getDeployNamePrefix() + botId;
        String volumePath = deployConfigs.getInternalBasePath() + deployName;
        String dataPath = volumePath + deployConfigs.getDataConfigsPath();
        Path socketPath = Paths.get(dataPath,"bot.socket");
        log.info("Socket path: {}", socketPath);
        return socketPath;
    }

}
