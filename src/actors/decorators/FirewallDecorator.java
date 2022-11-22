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

        boolean finished = false;

        //In case the process doesn't finish, we're still processing messages
        while (!finished) {
            try {
                Thread.sleep(2000); //Sleep the Thread
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (client.getQueue().isEmpty()) {

                System.out.println("No messages to process " + client.getName());
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
                client.getMessages();
                Message message = client.getQueue().poll(); //Get the first message and delete it
                if (message != null) {
                    if (!message.getMessage().equals("quite")) {

                        client.setMessageList("This is a FirewallDecorator, it's amazing this program!");
                        Actor newActor = message.getActor();

                        try {
                            Thread.sleep(2000); //Sleep the Thread
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        newActor.send(new Message(this, client.getMessageFromList()));
                        try {
                            Thread.sleep(2000); //Sleep the Thread
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    } else {

                        System.out.println("I received a quite message from " + message.getActor().getName());
                        finished = true;
                    }

                }

            }
        }

        System.out.println("Thread finished " + client.getName());
    }
}

