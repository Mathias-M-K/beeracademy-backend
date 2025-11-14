package dk.mathiaskofod.services.connection.models.events.game;

import dk.mathiaskofod.domain.game.events.events.StartGameEvent;
import dk.mathiaskofod.services.game.id.generator.models.GameId;

public record GameStartGameEventDto(GameId gameId) {

    public static GameStartGameEventDto fromGameEvent(StartGameEvent event) {
        return new GameStartGameEventDto(event.gameId());
    }

}
