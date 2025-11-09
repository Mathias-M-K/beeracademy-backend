package dk.mathiaskofod.services.player.exeptions;

import dk.mathiaskofod.providers.exeptions.BaseException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlayerNotFoundException extends BaseException {

    public PlayerNotFoundException(String playerId) {
        super(createMessage(playerId), 404);
        log.warn(createMessage(playerId));
    }

    private static String createMessage(String playerId) {
        return "Player with ID " + playerId + " not found.";
    }
}
