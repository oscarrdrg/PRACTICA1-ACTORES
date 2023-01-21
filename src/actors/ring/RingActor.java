package actors.ring;

import actors.Actor;
import message.Message;

/**
 * @author Oscar
 */
public class RingActor extends Actor {

    private Actor nextActorToConnect;


    public RingActor(String name) {
        super(name);
    }

    public Actor getNextActorToConnect() {
        return nextActorToConnect;
    }

    public void setNextActorToConnect(Actor nextActorToConnect) {
        this.nextActorToConnect = nextActorToConnect;
    }

    public void processMessages(Message message) {
        Actor newActor = message.getActor();

        try {
            Thread.sleep(2000); //Sleep the Thread
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        newActor.send(new Message(this, getMessageFromList()));
        if(nextActorToConnect!=null)
            nextActorToConnect.send(new Message(this, getMessageFromList()));
        try {
            Thread.sleep(2000); //Sleep the Thread
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

}


