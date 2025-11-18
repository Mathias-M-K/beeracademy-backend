package dk.mathiaskofod.services.session.models.actions.shared;

import dk.mathiaskofod.services.session.models.actions.game.client.GameClientAction;
import dk.mathiaskofod.services.session.models.actions.player.client.PlayerClientAction;
import dk.mathiaskofod.services.session.models.annotations.ActionType;

@ActionType("END_OF_TURN")
public record EndOfTurnAction(long duration) implements GameClientAction, PlayerClientAction {
}
