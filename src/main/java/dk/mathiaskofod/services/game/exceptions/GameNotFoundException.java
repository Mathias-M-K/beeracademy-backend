package dk.mathiaskofod.services.game.exceptions;

import dk.mathiaskofod.providers.exeptions.BaseException;
import dk.mathiaskofod.services.game.game.id.generator.models.GameId;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameNotFoundException extends BaseException {

    public GameNotFoundException(String gameId) {
        super(createMessage(gameId), 404);
        log.warn(createMessage(gameId));
    }

    private static String createMessage(String gameId) {
        String humanReadableId = new GameId(gameId).humanReadableId();
        return "Game with id " + humanReadableId + " not found";
    }
}
