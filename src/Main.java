import actors.*;
import actors.decorators.FirewallDecorator;
import actors.proxy.ActorProxy;
import actors.singleton.ActorContext;

import java.time.LocalDate;

/**
 * @author Oscar
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        intro();

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

    public static void intro(){

        System.out.println("\n\t**********************");
        System.out.println("\tACTORS EXERCISE");
        System.out.println("        by Óscar Rodrigo");
        System.out.println("\t**********************\n");
        System.out.println("Date: "+LocalDate.now());
        System.out.println("Course: TAP(CAT) (Advanced Programming Techniques)");
        System.out.println("Professor: Pedro Antonio García López");
        System.out.println("-------------------------------------\n");

    }
}