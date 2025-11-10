package dk.mathiaskofod.services.game;

import dk.mathiaskofod.services.auth.AuthService;
import dk.mathiaskofod.services.auth.models.Token;
import dk.mathiaskofod.services.game.game.id.generator.models.GameId;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.resteasy.reactive.common.NotImplementedYet;

@ApplicationScoped
public class GameClientConnectionService {

    @Inject
    GameService gameService;

    @Inject
    AuthService authService;

    public Token claimGame(GameId gameId){

        Game game = gameService.getGame(gameId);

        if(game.getConnectionInfo().isClaimed()){
            //FIXME this is not done
            throw new NotImplementedYet();
        }

        game.getConnectionInfo().setClaimed(true);

        return authService.createGameClientToken(gameId);
    }
}
