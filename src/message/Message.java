package message;

import actors.Actor;

/**
 * @author Oscar
 */
public record Message(Actor actor, String message) {
}
