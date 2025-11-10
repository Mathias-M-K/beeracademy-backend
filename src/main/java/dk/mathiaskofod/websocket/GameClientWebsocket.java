package dk.mathiaskofod.websocket;

import dk.mathiaskofod.services.auth.models.Roles;
import io.quarkus.websockets.next.*;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@RolesAllowed(Roles.GAME_ROLE)
@WebSocket(path = "/client")
public class GameClientWebsocket {

    @Inject
    WebSocketConnection connection;

    @OnOpen
    void onOpen(){
        log.info("new client connected");
    }

    @OnClose
    void onClose(){
        log.warn("Client disconnected");
    }

    @OnTextMessage
    void onMessage(String message){
        log.info("Client message!");
    }

}
