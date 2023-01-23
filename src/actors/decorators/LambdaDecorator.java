package actors.decorators;

import actors.Actor;
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
        //comprobar si es tipo closure y guardar en la lista el predciado -> infanceof
        //si no es, comprobar ->test predicade.test false no envia
        client.send(message);
    }

    public void processMessages(Message message) {
        client.processMessages(message);
    }
}
