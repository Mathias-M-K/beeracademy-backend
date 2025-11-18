package dk.mathiaskofod.domain.game.events;

import dk.mathiaskofod.services.game.id.generator.models.GameId;

public record EndGameEvent(GameId gameId) implements GameEvent{
}
