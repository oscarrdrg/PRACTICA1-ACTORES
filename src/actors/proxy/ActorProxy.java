package actors.proxy;

import actors.Actor;
import interfaces.SendMessage;
import message.Message;

/**
 * @author Oscar
 */
public class ActorProxy implements SendMessage {

    /*It's important to mention that we don't really need the Actor functions
    so for that reason is better get an Actor reference than extend of an Actor Class */
    Actor actor; //Reference to actor

    public ActorProxy(String name) {
        actor = new Actor(name);

    }

    public void send(Message message) {
        actor.send(message); //Call the function "send" of the Actor reference to add those messages in his queue
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

}
