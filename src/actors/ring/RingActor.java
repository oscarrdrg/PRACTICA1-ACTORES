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

    public void processMessages() {

        boolean finished = false, firstCommunication = false;

        //In case the process doesn't finish, we're still processing messages
        while (!finished) {
            try {
                Thread.sleep(2000); //Sleep the Thread
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (getQueue().isEmpty()) {

                System.out.println("No messages to process: " + getName());
                try {
                    Thread.sleep(2000); //Sleep the Thread to process messages in case queue is empty
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            } else {
                try {
                    Thread.sleep(2000); //Sleep the Thread
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                getMessages();
                Message message = getQueue().poll(); //Get the first message and delete it
                if (message != null) {
                    if (!message.getMessage().equals("quite")) {

                        Actor newActor = message.getActor();

                        try {
                            Thread.sleep(2000); //Sleep the Thread
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        newActor.send(new Message(this, getMessageFromList()));
                        try {
                            Thread.sleep(2000); //Sleep the Thread
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        if (!firstCommunication) {
                            try {
                                Thread.sleep(2000); //Sleep the Thread to process messages in case queue is empty
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                            nextActorToConnect.send(new Message(this, "Start communication")); //We send a message to our Actor reference to start the communication
                            firstCommunication = true; //Set first communication to true, we check that we are not going to send this message again
                        }

                    } else {

                        System.out.println("I received a quite message from " + message.getActor().getName());
                        finished = true;

                    }

                }

            }
        }

        System.out.println("Thread finished " + getName());


    }
}
