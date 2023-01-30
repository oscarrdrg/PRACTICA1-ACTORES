package actors.decorators;

import actors.Actor;
import message.Message;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

public class LambdaFirewallDecorator extends Actor {
    Actor client;
    LinkedList<Predicate<Message>> list = new LinkedList<>();

    public LambdaFirewallDecorator(Actor client) {
        super(client.getName() + " lambda decorator");
        this.client = client;
        addClosureMessage();
    }

    public void send(Message message) {
        boolean blocked = false;
       for(Predicate<Message> predicate : list){
           if(!predicate.test(message)) blocked = true;
       }

       if(blocked) System.out.println("Message blocked\n");
       else client.send(message);
    }

    public void addClosure(Predicate<Message> predicate){
        list.add(predicate);
    }

    public void addClosureMessage() {
        list.add(message -> !message.getMessage().contains("spam"));
        list.add(message -> !message.getMessage().contains("virus"));
    }

    public void processMessages(Message message) {
        client.processMessages(message);
    }
}
