package dk.mathiaskofod.services.game.exceptions.deck;

import dk.mathiaskofod.providers.exceptions.BaseException;
import dk.mathiaskofod.domain.game.deck.models.Suit;

public class NotEnoughSuitesAvailableException extends BaseException {

    publ    public NotEnoughSuitesAvailableException(int nrOfRequestedSuits) {
        super(String.format("Requested %d suits, but only %d are available", nrOfRequestedSuits, Suit.values().length), 500);
    }
}
