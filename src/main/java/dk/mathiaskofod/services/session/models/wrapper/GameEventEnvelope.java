package dk.mathiaskofod.services.session.models.wrapper;


import dk.mathiaskofod.services.session.models.annotations.Category;
import dk.mathiaskofod.services.session.models.events.game.GameEventDto;


@Category("GAME_EVENT")
public record GameEventEnvelope(GameEventDto payload) implements WebsocketEnvelope {
}
