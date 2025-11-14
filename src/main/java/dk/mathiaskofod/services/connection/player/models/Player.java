package dk.mathiaskofod.services.connection.player.models;

import dk.mathiaskofod.services.common.models.ConnectionInfo;
import dk.mathiaskofod.services.game.id.generator.IdGenerator;
import dk.mathiaskofod.domain.game.models.Stats;

//FIXME: the player object should live with the Game in the domain package. See PlayerStructureSuggestion.md for more info.
public record Player(String name, String id, Stats stats, ConnectionInfo connectionInfo) {

    public static Player create(String name){
        String id = IdGenerator.generatePlayerId();
        return new Player(name, id, new Stats(), new ConnectionInfo());

    }
}
