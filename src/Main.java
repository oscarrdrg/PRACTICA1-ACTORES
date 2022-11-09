import actors.*;
import message.Message;

/**
 * @author Oscar
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        //Create the proxies
        ActorProxy proxy = ActorContext.spawnActor(new Actor("Oscar"));
        ActorProxy proxy2 = ActorContext.spawnActor(new Actor("Javi"));
        //ActorProxy decorator = ActorContext.spawnActor(new FirewallDecorator(new Actor("Prueba")));

        //Starting the communication
        proxy.send(new Message(proxy2.getActor(), "Start the communication"));


        //See the actors in context
        ActorContext.getActorsFromContext();

    }
}