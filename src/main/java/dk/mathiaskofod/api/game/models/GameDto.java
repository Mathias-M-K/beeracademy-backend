package dk.mathiaskofod.api.game.models;

import dk.mathiaskofod.domain.game.Game;
import dk.mathiaskofod.domain.game.GameImpl;

import java.util.List;

public record GameDto(String name, String id, boolean isConnected, int nrOfPlayers) {

    public static GameDto fromGame(Game game){

        List<PlayerDto> players = game.getPlayers().stream()
                .map(PlayerDto::fromPlayer)
                .toList();

        return new GameDto(
                game.getName(),
                game.getGameId().humanReadableId(),
                game.getConnectionInfo().isConnected(),
                players.size()
        );
    }
}
