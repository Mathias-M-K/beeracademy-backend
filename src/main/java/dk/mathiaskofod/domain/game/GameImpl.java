package dk.mathiaskofod.domain.game;

import dk.mathiaskofod.domain.game.events.GameEventEmitter;
import dk.mathiaskofod.services.common.models.ConnectionInfo;
import dk.mathiaskofod.domain.game.deck.Deck;
import dk.mathiaskofod.domain.game.exceptions.GameNotStartedException;
import dk.mathiaskofod.services.game.id.generator.models.GameId;
import dk.mathiaskofod.domain.game.deck.models.Card;
import dk.mathiaskofod.domain.game.models.Chug;
import dk.mathiaskofod.domain.game.models.Turn;
import dk.mathiaskofod.services.player.models.Player;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.common.NotImplementedYet;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Slf4j
public class GameImpl implements Game{

    @Getter
    private final String name;

    @Getter
    private final GameId gameId;

    @Getter
    private final List<Player> players;

    @Getter
    private final ConnectionInfo connectionInfo;

    private boolean isStarted = false;
    private Instant gameStartTime;
    private int round = 1;
    private final Deck deck;


    private Player currentPlayer;
    private int currentPlayerIndex;
    private Instant currentPlayerStartTime;

    GameEventEmitter eventEmitter;

    public GameImpl(String name, GameId gameId, List<Player> players, GameEventEmitter eventEmitter) {
        this.name = name;
        this.gameId = gameId;
        this.players = players;
        this.connectionInfo = new ConnectionInfo();

        this.currentPlayer = players.getFirst();
        this.currentPlayerIndex = 0;
        this.deck = new Deck(players.size());

        this.eventEmitter = eventEmitter;
    }

    public void startGame() {
        isStarted = true;
        gameStartTime = Instant.now();
        currentPlayerStartTime = Instant.now();
    }

    public void endGame(){

    }

    private Card drawCard(){
        return deck.drawCard();
    }

    private Duration getElapsedGameTime() {
        if (!isStarted) {
            throw new GameNotStartedException(gameId);
        }
        return Duration.between(gameStartTime, Instant.now());
    }

    public void endTurnBy(Player player, long clientDurationMillis){
        endTurnBy(player, clientDurationMillis,null);
    }

    public void endTurnBy(Player player, long clientDurationMillis, Chug chug) {

        if (!isStarted) {
            throw new GameNotStartedException(gameId);
        }

        if(player != currentPlayer){
            throw new NotImplementedYet();
        }

        //TODO research this. Like what do we do with time and sync
        Duration serverTime = Duration.between(currentPlayerStartTime, Instant.now());
        Duration clientTime = Duration.ofMillis(clientDurationMillis);
        Duration clientDiff = Duration.ofMillis(clientDurationMillis - serverTime.toMillis());
        Duration playerTime = round == 1 ? Duration.ofMinutes(0) : clientTime;

        log.info("Client diff from server time: {} millis", clientDiff.toMillis());

        Card card = drawCard();
        Turn turn = new Turn(round, card, playerTime);
        currentPlayer.stats().addTurn(turn);

        if(chug != null){
            currentPlayer.stats().addChug(chug);
        }

        progressGame();

        eventEmitter.onEndOfTurn(playerTime,card,currentPlayer, gameId);
    }

    private void progressGame() {
        currentPlayer = getNextPlayer();
        currentPlayerStartTime = Instant.now();
    }

    private Player getNextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex > players.size() - 1) {
            round++;
            currentPlayerIndex = 0;
        }

        return players.get(currentPlayerIndex);
    }


}
