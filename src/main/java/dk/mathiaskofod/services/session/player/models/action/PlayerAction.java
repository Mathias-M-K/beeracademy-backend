package dk.mathiaskofod.services.session.player.models.action;

import java.util.Map;

public record PlayerAction(PlayerActionType type, Map<PlayerDataType, Object> data) {


}
