package dk.mathiaskofod.websocket;

import dk.mathiaskofod.providers.exceptions.mappers.ExceptionResponse;
import dk.mathiaskofod.services.auth.models.Roles;
import dk.mathiaskofod.services.auth.models.TokenInfo;
import dk.mathiaskofod.services.game.exceptions.GameNotFoundException;
import dk.mathiaskofod.services.connection.player.PlayerClientConnectionService;
import dk.mathiaskofod.services.connection.player.models.action.PlayerAction;
import dk.mathiaskofod.websocket.models.CustomWebsocketCodes;
import io.quarkus.websockets.next.*;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.jwt.JsonWebToken;


@Slf4j
@RolesAllowed(Roles.PLAYER_ROLE)
@WebSocket(path = "/player")
public class PlayerClientWebsocket {

    @Inject
    JsonWebToken jwt;

    @Inject
    WebSocketConnection connection;

    @Inject
    PlayerClientConnectionService playerClientConnectionService;

    @OnOpen(broadcast = true)
    public void onOpen() {

        TokenInfo tokenInfo = TokenInfo.fromToken(jwt);
        String websocketConnId = connection.id();

        playerClientConnectionService.registerConnection(tokenInfo, websocketConnId);
    }

    @OnClose
    public void onClose(CloseReason reason) {

        if(reason.getCode() == CustomWebsocketCodes.SESSION_NOT_FOUND.getCode()){
            return;
        }

        playerClientConnectionService.registerDisconnect(TokenInfo.fromToken(jwt));
    }

    @OnTextMessage()
    public void onMessage(PlayerAction action) {
        TokenInfo tokenInfo = TokenInfo.fromToken(jwt);
        log.info("Received action from {}: {}", tokenInfo.playerName(), action);
        playerClientConnectionService.onPlayerAction(action,tokenInfo);
    }

    @OnError
    public void onError(RuntimeException e){
        String cause = e.getCause() == null ? "" : e.getCause().getClass().getSimpleName();
        ExceptionResponse response = new ExceptionResponse(e.getClass().getSimpleName(),cause,e.getMessage());
        log.warn("Websocket error for connection {}: {}", connection.id(), response);
        connection.sendTextAndAwait(response);

        if(e instanceof GameNotFoundException){
            connection.closeAndAwait(new CloseReason(CustomWebsocketCodes.SESSION_NOT_FOUND.getCode()));
        }

    }


}
