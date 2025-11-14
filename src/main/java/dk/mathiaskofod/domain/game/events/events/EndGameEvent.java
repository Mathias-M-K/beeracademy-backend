package dk.mathiaskofod.domain.game.events.events;

import dk.mathiaskofod.services.game.id.generator.models.GameId;

public record EndGameEvent(GameId gameId) implements GameEvent{
}
