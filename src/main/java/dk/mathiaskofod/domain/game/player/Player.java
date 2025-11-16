package dk.mathiaskofod.domain.game.player;

import dk.mathiaskofod.services.common.models.ConnectionInfo;
import dk.mathiaskofod.services.game.id.generator.IdGenerator;
import dk.mathiaskofod.domain.game.player.models.Stats;

//FIXME: the player object should live with the Game in the domain package. See PlayerStructureSuggestion.md for more info.
public record Player(String name, String id, Stats stats) {

    public static Player create(String name){
        String id = IdGenerator.generatePlayerId();
        return new Player(name, id, new Stats());

    }
}
