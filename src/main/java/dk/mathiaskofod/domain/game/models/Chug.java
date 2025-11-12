package dk.mathiaskofod.domain.game.models;

import dk.mathiaskofod.domain.game.deck.models.Card;

import java.time.Duration;

public record Chug(Card card, Duration chugTime) {
}
