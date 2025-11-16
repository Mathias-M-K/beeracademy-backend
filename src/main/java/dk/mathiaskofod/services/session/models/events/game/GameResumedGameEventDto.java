package dk.mathiaskofod.services.session.models.events.game;

import dk.mathiaskofod.domain.game.events.events.GameEvent;
import dk.mathiaskofod.domain.game.events.events.ResumeGameEvent;
import dk.mathiaskofod.services.game.id.generator.models.GameId;

public record GameResumedGameEventDto(GameId gameId) implements GameEvent {

    public static GameResumedGameEventDto fromGameEvent(ResumeGameEvent event) {
        return new GameResumedGameEventDto(event.gameId());
    }
}
