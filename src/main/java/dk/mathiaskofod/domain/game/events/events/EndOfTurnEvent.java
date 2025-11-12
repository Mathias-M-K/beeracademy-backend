package dk.mathiaskofod.domain.game.events.events;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import dk.mathiaskofod.services.game.id.generator.models.GameId;
import dk.mathiaskofod.domain.game.deck.models.Card;

public record EndOfTurnEvent(long elapsedTimeMillis, Card card, String newPlayerId, @JsonUnwrapped GameId gameId) implements GameEvent {
}
