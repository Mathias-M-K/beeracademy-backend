package dk.mathiaskofod.domain.game.events;

import dk.mathiaskofod.domain.game.models.Chug;
import dk.mathiaskofod.domain.game.models.Turn;
import dk.mathiaskofod.services.game.id.generator.models.GameId;
import dk.mathiaskofod.services.connection.player.models.Player;

import java.time.Duration;

public interface GameEventEmitter {

    void onStartGame(GameId gameId);

    void onEndGame(GameId gameId, Duration gameDuration);

    void onEndOfTurn(Turn turn, Player previousPlayer, Player newPlayer, Player nextPlayer, GameId gameId);

    void onNewChug(Chug chug, Player player, GameId gameId);

    void onPauseGame(GameId gameId);

    void onResumeGame(GameId gameId);

}
