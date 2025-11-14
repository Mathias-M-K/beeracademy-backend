package dk.mathiaskofod.domain.game.events.events;

import dk.mathiaskofod.services.game.id.generator.models.GameId;

public record ResumeGameEvent(GameId gameId) implements GameEvent {
}
