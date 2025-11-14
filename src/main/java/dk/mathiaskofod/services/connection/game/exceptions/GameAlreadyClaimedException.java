package dk.mathiaskofod.services.connection.game.exceptions;

import dk.mathiaskofod.providers.exceptions.BaseException;
import dk.mathiaskofod.services.game.id.generator.models.GameId;

public class GameAlreadyClaimedException extends BaseException {

    public GameAlreadyClaimedException(GameId gameId) {
        super(String.format("Could not find a game, matching ID: %s", gameId.humanReadableId()), 400);
    }
}
