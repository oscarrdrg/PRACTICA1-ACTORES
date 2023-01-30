package main;

import actors.HelloWorldActor;
import actors.InsultActor;
import actors.decorators.EncryptionDecorator;
import actors.decorators.FirewallDecorator;
import actors.decorators.LambdaFirewallDecorator;
import actors.observer.ActorListener;
import actors.proxys.ActorProxy;
import actors.proxys.ActorProxyExtended;
import actors.proxys.DynamicProxy;
import actors.ring.RingActor;
import actors.singleton.ActorContext;
import interfaces.InsultService;
import message.ClosureMessage;
import message.Message;
import message.MessageMain;
import services.InsultServiceImpl;
import services.MonitorService;

import java.time.LocalDate;

/**
 * @author Oscar
 */
public class StartProgram {

    public static void startProgram() {

        /* Initialize the Rings Actors */
        RingActor ringActor = new RingActor("Oscar");
        RingActor ringActor2 = new RingActor("Peter");
        RingActor ringActor3 = new RingActor("William");

        HelloWorldActor hello_world_actor = new HelloWorldActor("HelloWorld");
        ActorListener listener = new ActorListener("Listener");

        /* Add the actor to MonitorService */
        MonitorService.addActorsMonitor(ringActor);
        MonitorService.addActorsMonitor(ringActor2);
        MonitorService.addActorsMonitor(ringActor3);

        //Create the proxies
        ActorProxy proxy = ActorContext.spawnActor(ringActor);
        ActorProxy proxy2 = ActorContext.spawnActor(ringActor2);
        ActorProxy proxy3 = ActorContext.spawnActor(ringActor3);
        ActorProxy proxy4 = ActorContext.spawnActor(hello_world_actor);

        /* Decorators */
        ActorProxy decorator = ActorContext.spawnActor(new FirewallDecorator(proxy.getActor()));
        ActorProxy encrypt = ActorContext.spawnActor(new EncryptionDecorator(decorator.getActor()));
        LambdaFirewallDecorator lambdaFirewallDecorator = new LambdaFirewallDecorator(encrypt.getActor());
        lambdaFirewallDecorator.addClosure(message -> !message.getMessage().contains("scam"));
        ActorProxy lambda = ActorContext.spawnActor(lambdaFirewallDecorator);


        /* Set the Ring Actors in the ring */
        ringActor.setNextActorToConnect(ringActor2);
        ringActor2.setNextActorToConnect(ringActor3);
        ringActor3.setNextActorToConnect(ringActor);

        /* Receive prove */
        ActorProxy insult = ActorContext.spawnActor(new InsultActor("InsultActor"));
        ActorProxyExtended actorProxyExtended = new ActorProxyExtended("Extended");
        insult.setActorProxyExtended(actorProxyExtended);
        insult.send(new MessageMain(("Hello from Main")));
        String message = insult.receive();
        System.out.println("\nMain message, receive is working well -> " + message);


        /* Dynamic proxy */
        InsultService service = (InsultService) DynamicProxy.newInstance(new InsultServiceImpl(), insult);
        service.addInsult("Silly");

        try {
            Thread.sleep(2000); //Sleep the Thread to print well the Actors in context
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //See the actors in context
        ActorContext.getActorsFromContext();


        //Starting the communication
        lambda.send(new Message(proxy4.getActor(), "This is a virus message"));
        lambda.send(new Message(proxy3.getActor(), "This is a spam message"));
        lambda.send(new Message(proxy3.getActor(), "This is a scam message"));
        lambda.send(new Message(proxy4.getActor(), "Start communication"));

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
