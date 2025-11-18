package dk.mathiaskofod.domain.game.events;

import dk.mathiaskofod.domain.game.models.Turn;
import dk.mathiaskofod.domain.game.player.Player;
import dk.mathiaskofod.services.game.id.generator.models.GameId;

public record EndOfTurnEvent(Turn turn, Player previusPlayer, Player player, Player nextPlayer, GameId gameId) implements GameEvent {
}
