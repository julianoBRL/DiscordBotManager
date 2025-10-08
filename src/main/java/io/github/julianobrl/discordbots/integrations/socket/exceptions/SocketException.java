package io.github.julianobrl.discordbots.integrations.socket.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class SocketException extends RuntimeException {

    @Getter
    private HttpStatus status;

    public SocketException() {
        super();
    }
    public SocketException(String message) {
        super(message);
    }
    public SocketException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    public SocketException(String message, Throwable cause) {
        super(message, cause);
    }
    public SocketException(Throwable cause) {
        super(cause);
    }
    protected SocketException(String message, Throwable cause,
                              boolean enableSuppression,
                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
