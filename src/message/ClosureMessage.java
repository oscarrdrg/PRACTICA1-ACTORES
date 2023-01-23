package message;

import actors.Actor;

public class ClosureMessage extends Message{
    public ClosureMessage(Actor actor, String message) {
        super(actor, message);
    }
}
