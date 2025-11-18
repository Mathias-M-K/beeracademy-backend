package dk.mathiaskofod.domain.game.events;

import dk.mathiaskofod.services.game.id.generator.models.GameId;

public record ResumeGameEvent(GameId gameId) implements GameEvent {
}
