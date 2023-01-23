package actors.proxys;

import actors.Actor;
import interfaces.Observer;
import interfaces.SendMessage;
import message.Message;

/**
 * @author Oscar
 */
public class ActorProxy implements SendMessage {

    /*It's important to mention that we don't really need the Actor functions
    so for that reason is better get an Actor reference than extend of an Actor Class */
    Actor actor; //Reference to actor
    ActorProxyExtended actorProxyExtended;

    public ActorProxy(String name) {
        actor = new Actor(name);
    }

    public void send(Message message) {
        if(actorProxyExtended!=null) actorProxyExtended.send(message);
        actor.send(message); //Call the function "send" of the Actor reference to add those messages in his queue
    }

    public String receive(){
        actorProxyExtended.getMessageLinkedList().poll();
        return actor.getMessageFromList();
    }
    public ActorProxyExtended getActorProxyExtended() {
        return actorProxyExtended;
    }

    public void setActorProxyExtended(ActorProxyExtended actorProxyExtended) {
        this.actorProxyExtended = actorProxyExtended;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

}
