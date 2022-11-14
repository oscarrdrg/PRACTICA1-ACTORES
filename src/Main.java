import actors.*;
import actors.decorators.FirewallDecorator;
import actors.proxy.ActorProxy;
import actors.singleton.ActorContext;
import message.Message;

import java.time.LocalDate;

/**
 * @author Oscar
 */
public class Main {
    public static void main(String[] args) {

        intro();

        //Create the proxies
        ActorProxy proxy = ActorContext.spawnActor(new Actor("Oscar"));
        ActorProxy proxy2 = ActorContext.spawnActor(new Actor("Peter"));
        ActorProxy proxy3 = ActorContext.spawnActor(new Actor("William"));
        ActorProxy decorator = ActorContext.spawnActor(new FirewallDecorator(proxy.getActor()));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //See the actors in context
        ActorContext.getActorsFromContext();

        proxy.getActor().setNextActorToConnect(proxy2.getActor());
        proxy2.getActor().setNextActorToConnect(proxy3.getActor());
        proxy3.getActor().setNextActorToConnect(decorator.getActor());
        decorator.getActor().setNextActorToConnect(proxy.getActor());

        //Starting the communication
        proxy.send(new Message(decorator.getActor(), "Start communication"));

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