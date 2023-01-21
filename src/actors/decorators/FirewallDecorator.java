package actors.decorators;

import actors.Actor;
import actors.singleton.ActorContext;
import message.Message;

/**
 * @author Oscar
 */

public class FirewallDecorator extends Actor {
    Actor client;

    public FirewallDecorator(Actor client) {
        super(client.getName() + " firewall decorator");
        client.setMessageList("Hello from Firewall Decorator");
        this.client = client;
    }

    public void send(Message message) {
        getQueue().add(message);
    }


    public void processMessages(Message message) {
        if (ActorContext.getActorList().containsKey(message.getActor().getName())){
            client.send(message);
            System.out.println("HE LLEGADO AL FIREWALL ");
        }

        else {
            Actor newActor = message.getActor();
            newActor.send(new Message(this, "Communication error, you cannot access this actor"));
        }
    }
}

