package dk.mathiaskofod.services.game;

import dk.mathiaskofod.api.game.models.CreateGameRequest;
import dk.mathiaskofod.domain.game.GameImpl;
import dk.mathiaskofod.domain.game.events.GameEventEmitterImpl;
import dk.mathiaskofod.services.game.exceptions.GameNotFoundException;
import dk.mathiaskofod.services.game.id.generator.IdGenerator;
import dk.mathiaskofod.services.game.id.generator.models.GameId;
import dk.mathiaskofod.services.player.PlayerClientConnectionService;
import dk.mathiaskofod.services.player.exeptions.PlayerNotFoundException;
import dk.mathiaskofod.services.player.models.Player;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.resteasy.reactive.common.NotImplementedYet;

import java.util.*;

@ApplicationScoped
public class GameService {

    @Inject
    PlayerClientConnectionService playerClientConnectionService;

    @Inject
    GameEventEmitterImpl gameEventEmitterImpl;

    private final Map<GameId, GameImpl> games = new HashMap<>();

    public GameId createGame(CreateGameRequest createGameRequest) {
        return createGame(createGameRequest.name(), createGameRequest.playerNames());
    }

    public GameId createGame(String name, List<String> playerNames) {

        List<Player> players = playerNames.stream()
                .map(Player::create)
                .toList();

        GameId gameId = IdGenerator.generateGameId();

        GameImpl game = new GameImpl(name, gameId, players, gameEventEmitterImpl);
        games.put(gameId, game);

        return gameId;
    }

    public List<GameImpl> getGames() {
        return games.values().stream().toList();
    }

    public GameImpl getGame(GameId gameId) {
        if (games.containsKey(gameId)) {
            return games.get(gameId);
        } else {
            throw new GameNotFoundException(gameId);
        }
    }

    public Player getPlayer(GameId gameId, String playerId) {
        return getGame(gameId).getPlayers().stream()
                .filter(player -> player.id().equals(playerId))
                .findFirst()
                .orElseThrow(() -> new PlayerNotFoundException(playerId, gameId));
    }

    public void endOfTurn(long elapsedTime, GameId gameId, String playerId) {
        GameImpl game = getGame(gameId);
        Player player = getPlayer(gameId, playerId);
        game.endTurnBy(player, elapsedTime);
    }
}
