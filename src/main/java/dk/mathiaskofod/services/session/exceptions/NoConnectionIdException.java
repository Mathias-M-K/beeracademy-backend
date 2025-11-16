package dk.mathiaskofod.services.session.exceptions;

import dk.mathiaskofod.providers.exceptions.BaseException;
import dk.mathiaskofod.services.game.id.generator.models.GameId;

public class NoConnectionIdException extends BaseException {

    public NoConnectionIdException(String playerId, GameId gameId) {
        super("No connection ID exist for player " + playerId + " in game " + gameId.humanReadableId(), 404);
    }

    public NoConnectionIdException(GameId gameId){
        super("No connection ID exist for game " + gameId.humanReadableId(),404);
    }
}
