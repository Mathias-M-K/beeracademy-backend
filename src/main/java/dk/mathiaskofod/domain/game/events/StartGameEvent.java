package dk.mathiaskofod.domain.game.events;

import dk.mathiaskofod.services.game.id.generator.models.GameId;

public record StartGameEvent(GameId gameId) implements GameEvent {
}
