package actors.proxys;

import actors.Actor;
import interfaces.Observer;
import interfaces.SendMessage;
import message.Message;
import services.MonitorService;

/**
 * @author Oscar
 */
public class ActorProxy implements SendMessage, Observer {

    /*It's important to mention that we don't really need the Actor functions
    so for that reason is better get an Actor reference than extend of an Actor Class */
    Actor actor; //Reference to actor
    MonitorService service;

    public ActorProxy(String name, MonitorService service) {
        actor = new Actor(name);
        this.service = service;
        service.notifyMessage("Creation");

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

    @Override
    public void creation() {
        System.out.println("Actor " + getActor().getName() + " created");
    }

    @Override
    public void finalization() {
        System.out.println("Actor " + getActor().getName() + " finalized");
    }

    @Override
    public void incorrect_finalization() {

    }

    @Override
    public void received_message() {
        System.out.println("Actor " + getActor().getName() + " has received a message");
    }
}
