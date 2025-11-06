package dk.mathiaskofod.api.game;

import dk.mathiaskofod.services.game.GameService;
import dk.mathiaskofod.services.game.models.Game;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

@Path("/game")
public class GameApi {

    @Inject
    GameService gameService;

    @POST
    @ResponseStatus(200)
    public void createGame(@QueryParam("name") String name) {
        gameService.createGame(name);
    }

    @GET
    public List<Game> getGames() {
        return gameService.getGames();
    }
}
