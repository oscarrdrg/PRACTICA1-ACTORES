package actors.decorators;

import actors.Actor;
import message.Message;

/**
 * @author Oscar
 */

public class FirewallDecorator extends Actor {
    Actor client;

    public FirewallDecorator(Actor client) {
        super(client.getName() + " decorator");
        this.client = client;
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

                System.out.println("No messages to process " + getName());
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

                        setMessageList("This is a FirewallDecorator, it's amazing this program!");
                        Actor newActor = message.getActor();
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
                            getNextActorToConnect().send(new Message(this, "Start communication"));
                            firstCommunication = true;
                        }

                    } else {

                        System.out.println("I received a quite message from " + message.getActor().getName());
                        finished = true;
                    }

                }

            }
        }
    }

    public void getMessages() {
        if (!getQueue().isEmpty()) {
            System.out.println("\nactor " + getName() + " list message");
            System.out.println("--------------------------------------");
            getQueue().forEach(m -> System.out.println(m.getActor().getName() + " says " + "\"" + m.getMessage() + "\"" + " to " + getName()));
            System.out.println("\n");
        }
    }
}

