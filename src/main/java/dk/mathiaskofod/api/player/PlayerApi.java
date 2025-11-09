package dk.mathiaskofod.api.player;

import dk.mathiaskofod.api.game.models.PlayerDto;
import dk.mathiaskofod.services.game.GameService;
import dk.mathiaskofod.services.game.game.id.generator.models.GameId;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Path("/players")
public class PlayerApi {

    @Inject
    GameService gameService;

    @GET
    public List<PlayerDto> getPlayers(@Valid @QueryParam("game-id") GameId gameId) {
        return gameService.getPlayersInGame(gameId).stream()
                .map(PlayerDto::fromPlayer)
                .toList();
    }
}
