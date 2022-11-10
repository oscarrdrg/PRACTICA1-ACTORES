package actors;

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
            if (getQueue().isEmpty()) {

                System.out.println("No messages to process " + getName() + " decorator");
                try {
                    Thread.sleep(2000); //Sleep the Thread to process messages in case queue is empty
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            } else {
                getMessages();
                Message message = getQueue().poll(); //Get the first message and delete it
                if (message != null) {
                    if (!message.getMessage().equals("quite")) {

                        setMessageList("This is a FirewallDecorator, it's amazing this program!");
                        Actor newActor = message.getActor();
                        newActor.send(new Message(this, getMessageFromList()));

                    } else {

                        System.out.println("I received a quite message from " + message.getActor().getName());
                        finished = true;

                    }

                }

            }
        }
    }

    public void getMessages() {
        System.out.println("\nactor " + getName() + " list message");
        System.out.println("--------------------------------------");
        getQueue().forEach(m -> System.out.println(m.getActor().getName() + " says " + "\"" + m.getMessage() + "\"" + " to " + getName() + " decorator"));
        System.out.println("\n");
    }

}

