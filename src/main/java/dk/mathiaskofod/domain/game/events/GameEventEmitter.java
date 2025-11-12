package dk.mathiaskofod.domain.game.events;

import dk.mathiaskofod.domain.game.deck.models.Card;
import dk.mathiaskofod.services.game.id.generator.models.GameId;
import dk.mathiaskofod.services.player.models.Player;

import java.time.Duration;

public interface GameEventEmitter {

    void onEndOfTurn(Duration elapsedTime, Card card, Player newPlayer, GameId gameId);

    void onNewChug(Duration duration, Player player, GameId gameId);

    void onPauseGame(GameId gameId);

    void onResumeGame(GameId gameId);

}
