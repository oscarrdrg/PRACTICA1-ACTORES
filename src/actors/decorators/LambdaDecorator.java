package actors.decorators;

import actors.Actor;
import message.ClosureMessage;
import message.Message;

import java.util.LinkedList;
import java.util.function.Predicate;

public class LambdaDecorator extends Actor {
    Actor client;
    LinkedList<Predicate<Message>> list = new LinkedList<>();

    public LambdaDecorator(Actor client) {
        super(client.getName() + " lambda decorator");
        this.client = client;
    }

    public void send(Message message) {
        if(message instanceof ClosureMessage) list.add(m -> m.getMessage().equals("How are you?"));
        list.forEach(messagePredicate -> {if (messagePredicate.test(message)==true) client.send(message);});
    }

    public void processMessages(Message message) {
        client.processMessages(message);
    }
}
