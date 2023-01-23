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
        if (ActorContext.getActorList().containsKey(message.getActor().getName())){
            System.out.println(message.getMessage());
            System.out.println("Approved access from FirewallDecorator ");
            client.send(message);
        }
    }

    public void processMessages(Message message) {
        client.processMessages(message);

    }
}

