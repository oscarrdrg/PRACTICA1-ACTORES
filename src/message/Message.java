package message;

import actors.Actor;

/**
 * @author Oscar
 */


public class Message {
    Actor actor;
    String message;
    public Message(Actor actor, String message) {
        this.actor = actor;
        this.message = message;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}