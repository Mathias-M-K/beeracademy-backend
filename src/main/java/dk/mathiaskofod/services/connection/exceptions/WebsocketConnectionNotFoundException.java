package dk.mathiaskofod.services.connection.exceptions;

import dk.mathiaskofod.providers.exceptions.BaseException;

public class WebsocketConnectionNotFoundException extends BaseException {
    public WebsocketConnectionNotFoundException(String connectionId) {
        super(String.format("Couldn't find websocket connection: %s", connectionId), 500);
    }
}
