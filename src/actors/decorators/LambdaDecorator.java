package actors.decorators;

import actors.Actor;
import message.Message;

public class LambdaDecorator extends Actor {
    Actor client;

    public LambdaDecorator(Actor client) {
        super(client.getName() + " lambda decorator");
        this.client = client;
    }

    public void send(Message message) {
        getQueue().add(message);
    }

    public void processMessages(Message message) {
        //hacer algo
        client.send(message);
    }
}