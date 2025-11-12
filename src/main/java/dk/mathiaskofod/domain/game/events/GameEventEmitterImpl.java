package dk.mathiaskofod.domain.game.events;

import dk.mathiaskofod.domain.game.events.events.EndOfTurnEvent;
import dk.mathiaskofod.services.game.id.generator.models.GameId;
import dk.mathiaskofod.domain.game.deck.models.Card;
import dk.mathiaskofod.services.player.models.Player;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

import java.time.Duration;

@ApplicationScoped
public class GameEventEmitterImpl implements GameEventEmitter {

    @Inject
    Event<EndOfTurnEvent> eventBus;

    @Override
    public void onEndOfTurn(Duration elapsedTime, Card card, Player newPlayer, GameId gameId) {
        eventBus.fire(new EndOfTurnEvent(elapsedTime.toMillis(), card, newPlayer.id(), gameId));
    }

    @Override
    public void onNewChug(Duration duration, Player player, GameId gameId) {

    }

    @Override
    public void onPauseGame(GameId gameId) {

    }

    @Override
    public void onResumeGame(GameId gameId) {

    }
}
