package message;

import actors.Actor;

public class Message {

    private Actor actor;
    private String message;

    public Message(Actor actor, String message) {
        this.actor = actor;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor transmitter) {
        this.actor = transmitter;
    }
}
