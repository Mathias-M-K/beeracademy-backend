package dk.mathiaskofod.domain.game;

import dk.mathiaskofod.domain.game.models.Chug;
import dk.mathiaskofod.services.game.id.generator.models.GameId;
import dk.mathiaskofod.domain.game.player.Player;

import java.util.List;

public interface Game {

    String getName();

    GameId getGameId();

    /**
     * Starts the game by starting the timer for both the game and the first player
     */
    void startGame();

    /**
     * Ends the game, stopping all timers and finalizing the game state
     */
    void endGame();

    /**
     * Ends the current turn for a specific player and starts the next player's turn
     * @param player the player whose turn is ending
     * @param duration the duration of the turn in milliseconds
     */
    void endTurnBy(Player player, long duration);

    /**
     * Ends the current turn for a specific player with a chug and starts the next player's turn
     * @param player the player whose turn is ending
     * @param duration the duration of the turn in milliseconds
     * @param chug the chug by the player
     */
    void endTurnBy(Player player, long duration, Chug chug);

    /**
     * Gets the list of players in the game
     * @return the list of players
     */
    List<Player> getPlayers();

}
