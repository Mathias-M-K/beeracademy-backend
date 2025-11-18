package dk.mathiaskofod.services.session.models.wrapper;

import dk.mathiaskofod.services.session.models.actions.player.client.PlayerClientAction;
import dk.mathiaskofod.services.session.models.annotations.Category;

@Category("PLAYER_CLIENT_ACTION")
public record PlayerClientActionEnvelope(PlayerClientAction payload) implements WebsocketEnvelope {
}
