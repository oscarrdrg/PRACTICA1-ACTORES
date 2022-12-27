import actors.InsultActor;
import actors.decorators.EncryptionDecorator;
import actors.decorators.FirewallDecorator;
import actors.proxys.ActorProxy;
import actors.proxys.DynamicProxy;
import actors.ring.RingActor;
import actors.singleton.ActorContext;
import interfaces.InsultService;
import message.Message;
import services.InsultServiceImpl;
import services.MonitorService;

import java.time.LocalDate;

/**
 * @author Oscar
 */
public class StartProgram {

    public static void startProgram() {

        MonitorService monitorService = new MonitorService();
        /* Initialize the Rings Actors */
        RingActor ringActor = new RingActor("Oscar");
        RingActor ringActor2 = new RingActor("Peter");
        RingActor ringActor3 = new RingActor("William");

        /* Set the Ring Actors in the ring */
        ringActor.setNextActorToConnect(ringActor2);
        ringActor2.setNextActorToConnect(ringActor3);
        ringActor3.setNextActorToConnect(ringActor);

        //Create the proxies
        ActorProxy proxy = ActorContext.spawnActor(ringActor, monitorService);
        monitorService.addObservers(proxy);
        ActorProxy proxy2 = ActorContext.spawnActor(ringActor2, monitorService);
        monitorService.addObservers(proxy2);
        ActorProxy proxy3 = ActorContext.spawnActor(ringActor3, monitorService);
        monitorService.addObservers(proxy3);
        ActorProxy decorator = ActorContext.spawnActor(new FirewallDecorator(proxy.getActor()), monitorService);
        ActorProxy encrypt = ActorContext.spawnActor(new EncryptionDecorator(decorator.getActor()), monitorService);

        ActorProxy insult = ActorContext.spawnActor(new InsultActor("Prove"), monitorService);
        InsultService service = (InsultService) DynamicProxy.newInstance(new InsultServiceImpl(), insult);



        service.getInsult();

        try {
            Thread.sleep(2000); //Sleep the Thread to print well the Actors in context
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //See the actors in context
        ActorContext.getActorsFromContext();


        //Starting the communication
        encrypt.send(new Message(proxy2.getActor(), "Start Communication"));

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
