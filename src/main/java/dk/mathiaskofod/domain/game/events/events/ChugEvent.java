package dk.mathiaskofod.domain.game.events.events;

import dk.mathiaskofod.domain.game.models.Chug;
import dk.mathiaskofod.domain.game.player.Player;
import dk.mathiaskofod.services.game.id.generator.models.GameId;

public record ChugEvent(Chug chug, Player player, GameId gameId) implements GameEvent {
}
