package dk.mathiaskofod.services.connection.player.models.action;

import java.util.Map;

public record PlayerAction(PlayerActionType type, Map<PlayerDataType, Object> data) {


}
