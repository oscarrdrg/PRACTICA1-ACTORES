package actors.observer;

import actors.Actor;
import interfaces.Observer;

public class ActorListener extends Actor implements Observer {
    public ActorListener(String name) {
        super(name);
    }

    @Override
    public void creation(Actor actor) {
        System.out.println("\nActor Listener Observer pattern -> Actor " + actor.getName() + " created");
    }

    @Override
    public void finalization(Actor actor) {
        System.out.println("\nActor Listener Observer pattern -> Actor " + actor.getName() + " finalized");
    }

    @Override
    public void incorrect_finalization(Actor actor) {

    }

    @Override
    public void received_message(Actor actor) {
        System.out.println("\nActor Listener Observer pattern -> Actor " + actor.getName() + " has received a message");
    }

}
