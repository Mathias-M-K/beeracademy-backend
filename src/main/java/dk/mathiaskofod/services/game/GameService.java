package dk.mathiaskofod.services.game;

import dk.mathiaskofod.services.game.models.Game;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GameService {

    private List<Game> games = new ArrayList<>();

    public void createGame(String name){
        games.add(new Game(name));
    }

    public List<Game> getGames(){
        return games;
    }

}
