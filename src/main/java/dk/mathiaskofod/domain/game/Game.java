package dk.mathiaskofod.domain.game;

import dk.mathiaskofod.domain.game.models.Chug;
import dk.mathiaskofod.services.player.models.Player;

public interface Game {

    /**
     * Starts the game by starting the timer for both the game and the first player
     */
    void startGame();

    /**
     * Ends the current turn for a specific player and starts the next player's turn
     * @param player the player whose turn is ending
     * @param duration the duration of the turn in milliseconds
     */
    void endTurnBy(Player player, long duration);
    void endTurnBy(Player player, long duration, Chug chug);



}
