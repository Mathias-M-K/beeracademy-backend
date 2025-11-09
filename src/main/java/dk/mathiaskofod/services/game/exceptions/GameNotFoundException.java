package dk.mathiaskofod.services.game.exceptions;

import dk.mathiaskofod.providers.exeptions.BaseException;

public class GameNotFoundException extends BaseException {

    public GameNotFoundException(String message, int httpStatus) {
        super(message, httpStatus);
    }
}
