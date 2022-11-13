import actors.*;
import actors.decorators.FirewallDecorator;
import message.Message;

/**
 * @author Oscar
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        //Create the proxies
        ActorProxy proxy = ActorContext.spawnActor(new Actor("Oscar"));
        ActorProxy proxy2 = ActorContext.spawnActor(new Actor("Javi"));
        ActorProxy decorator = ActorContext.spawnActor(new FirewallDecorator(proxy.getActor()));

        //See the actors in context
        ActorContext.getActorsFromContext();

        //Starting the communication
        RingActor ringActor = new RingActor();
        ringActor.addProxies(proxy);
        ringActor.addProxies(proxy2);
        ringActor.addProxies(decorator);

        ringActor.startCommunication();


    }
}