package actors;

import java.util.HashMap;

/**
 * @author Oscar
 */
public class ActorContext {

    private static HashMap<String, Actor> actorList = new HashMap<>();

    /*In this function we create a proxy of an Actor reference,
    this will allow us to receive and send messages from other actors.*/
    public static ActorProxy spawnActor(Actor actor) {

        /*We put the actor name as a key, and actor reference as a value in HashMap List
        to keep all the actor whose are running at the system*/

        addActorToList(actor.getName(), actor); //Call the function AddToList

        ActorProxy newActorProxy = new ActorProxy(actor.getName());
        newActorProxy.setActor(actor); //Set the Actor reference to the proxy

        Thread thread = new Thread(newActorProxy.getActor()); //Create Thread
        thread.start();

        //We start the thread that will process the messages
        try {
            Thread.sleep(2000); //Sleep the Thread
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        return newActorProxy; //Return the proxy

    }

    //Function to add those actors in the HashMap List
    private static void addActorToList(String name, Actor actor) {
        actorList.put(name, actor);
    }

    public static void getActorsFromContext() {
        System.out.println("\nlist of actors in the system:");
        System.out.println("-------------------------------");

        actorList.forEach((k, v) -> System.out.println(k));
        System.out.println("\n");
    }

    public static HashMap<String, Actor> getActorList() {
        return actorList;
    }

    public static void setActorList(HashMap<String, Actor> actorList) {
        ActorContext.actorList = actorList;
    }
}
