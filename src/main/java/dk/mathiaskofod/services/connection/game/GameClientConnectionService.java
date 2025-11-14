package dk.mathiaskofod.services.connection.game;

import dk.mathiaskofod.domain.game.Game;
import dk.mathiaskofod.domain.game.events.events.*;
import dk.mathiaskofod.domain.game.events.events.ResumeGameEvent;
import dk.mathiaskofod.services.auth.AuthService;
import dk.mathiaskofod.services.auth.models.Token;
import dk.mathiaskofod.services.connection.exceptions.WebsocketConnectionNotFoundException;
import dk.mathiaskofod.services.connection.game.exceptions.GameAlreadyClaimedException;
import dk.mathiaskofod.services.connection.game.exceptions.GameNotClaimedException;
import dk.mathiaskofod.services.connection.exceptions.NoConnectionIdException;
import dk.mathiaskofod.services.common.models.ConnectionInfo;
import dk.mathiaskofod.services.connection.models.events.game.*;
import dk.mathiaskofod.services.game.GameService;
import dk.mathiaskofod.services.game.id.generator.models.GameId;
import io.quarkus.websockets.next.OpenConnections;
import io.quarkus.websockets.next.WebSocketConnection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class GameClientConnectionService {

    @Inject
    GameService gameService;

    @Inject
    AuthService authService;

    @Inject
    OpenConnections connections;

    public Token claimGame(GameId gameId) {

        Game game = gameService.getGame(gameId);

        if (game.getConnectionInfo().isClaimed()) {
            throw new GameAlreadyClaimedException(gameId);
        }

        game.getConnectionInfo().setClaimed(true);

        return authService.createGameClientToken(gameId);
    }

    public void registerConnection(GameId gameId, String websocketConnId) {

        Game game = gameService.getGame(gameId);
        ConnectionInfo connInfo = game.getConnectionInfo();

        if (!connInfo.isClaimed()) {
            throw new GameNotClaimedException(gameId);
        }

        connInfo.setConnected(true);
        connInfo.setConnectionId(websocketConnId);

        log.info("Websocket Connection: Type:New game client connection, GameID:{}, WebsocketConnID:{}", gameId.humanReadableId(), websocketConnId);

    }

    private WebSocketConnection getWebsocketConnection(GameId gameId) {

        String connectionId = gameService.getGame(gameId)
                .getConnectionInfo()
                .getConnectionId()
                .orElseThrow(() -> new NoConnectionIdException(gameId));

        return connections.findByConnectionId(connectionId)
                .orElseThrow(() -> new WebsocketConnectionNotFoundException(connectionId));
    }

    void onStartGameEvent(@Observes StartGameEvent event) {

        GameStartGameEventDto gameStartGameEventDto = GameStartGameEventDto.fromGameEvent(event);

        WebSocketConnection connection = getWebsocketConnection(event.gameId());
        connection.sendTextAndAwait(gameStartGameEventDto);
    }

    void onEndGameEvent(@Observes EndGameEvent event) {

        GameEndGameEventDto gameEndGameEventDto = GameEndGameEventDto.fromGameEvent(event);

        WebSocketConnection connection = getWebsocketConnection(event.gameId());
        connection.sendTextAndAwait(gameEndGameEventDto);
    }

    void onEndOfTurnEvent(@Observes EndOfTurnEvent event) {

        EndOfTurnGameEventDto endOfTurnGameEventDto = EndOfTurnGameEventDto.fromGameEvent(event);

        WebSocketConnection connection = getWebsocketConnection(event.gameId());
        connection.sendTextAndAwait(endOfTurnGameEventDto);
    }

    void onChugEvent(@Observes ChugEvent event) {
        ChugGameEventDto chugGameEventDto = ChugGameEventDto.fromGameEvent(event);

        WebSocketConnection connection = getWebsocketConnection(event.gameId());
        connection.sendTextAndAwait(chugGameEventDto);
    }

    void onPauseEvent(@Observes PauseGameEvent event) {

        GamePausedGameEventDto gamePausedGameEventDto = GamePausedGameEventDto.fromGameEvent(event);

        WebSocketConnection connection = getWebsocketConnection(event.gameId());
        connection.sendTextAndAwait(gamePausedGameEventDto);
    }

    void onResumeEvent(@Observes ResumeGameEvent event) {

        GameResumedGameEventDto resumeEventDto = GameResumedGameEventDto.fromGameEvent(event);

        WebSocketConnection connection = getWebsocketConnection(event.gameId());
        connection.sendTextAndAwait(resumeEventDto);
    }



}
