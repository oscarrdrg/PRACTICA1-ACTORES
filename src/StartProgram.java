import actors.decorators.EncryptionDecorator;
import actors.decorators.FirewallDecorator;
import actors.proxy.ActorProxy;
import actors.ring.RingActor;
import actors.singleton.ActorContext;
import message.Message;

import java.time.LocalDate;

/**
 * @author Oscar
 */
public class StartProgram {

    public static void startProgram() {

        /* Initialize the Rings Actors*/
        RingActor ringActor = new RingActor("Oscar");
        RingActor ringActor2 = new RingActor("Peter");
        RingActor ringActor3 = new RingActor("William");

        /* Set the Ring Actors in the ring */
        ringActor.setNextActorToConnect(ringActor2);
        ringActor2.setNextActorToConnect(ringActor3);
        ringActor3.setNextActorToConnect(ringActor);


        //Create the proxies
        ActorProxy proxy = ActorContext.spawnActor(ringActor);
        ActorProxy proxy2 = ActorContext.spawnActor(ringActor2);
        ActorProxy proxy3 = ActorContext.spawnActor(ringActor3);
        ActorProxy decorator = ActorContext.spawnActor(new FirewallDecorator(proxy.getActor()));
        ActorProxy encrypt = ActorContext.spawnActor(new EncryptionDecorator(decorator.getActor()));

        try {
            Thread.sleep(2000); //Sleep the Thread to print well the Actors in context
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //See the actors in context
        ActorContext.getActorsFromContext();


        //Starting the communication
        proxy.send(new Message(encrypt.getActor(), "Start Communication"));

    }

    public static void intro() {

        System.out.println("\n\t**********************");
        System.out.println("\tACTORS EXERCISE");
        System.out.println("        by Óscar Rodrigo");
        System.out.println("\t**********************\n");
        System.out.println("Date: " + LocalDate.now());
        System.out.println("Course: TAP(CAT) (Advanced Programming Techniques)");
        System.out.println("Professor: Pedro Antonio García López");
        System.out.println("-------------------------------------\n");

    }
}
