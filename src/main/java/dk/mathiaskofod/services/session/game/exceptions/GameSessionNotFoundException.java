package dk.mathiaskofod.services.session.game.exceptions;

import dk.mathiaskofod.providers.exceptions.BaseException;
import dk.mathiaskofod.services.game.id.generator.models.GameId;

public class GameSessionNotFoundException extends BaseException {
    public GameSessionNotFoundException(GameId gameId) {
        super(String.format("Could not find a game session matching ID: %s", gameId.humanReadableId()), 404);
    }
}
