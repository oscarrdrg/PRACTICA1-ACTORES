package actors;

import java.util.LinkedList;

import interfaces.SendMessage;
import message.Message;

/**
 * @author Oscar
 */

public class Actor implements SendMessage, Runnable {

    private final String name;
    private final LinkedList<Message> queue;

    private Actor nextActorToConnect;

    /*List of possibles messages that this actor could send to others*/
    private final LinkedList<String> messageList = new LinkedList<>();

    public Actor(String name) {
        this.name = name;
        queue = new LinkedList<>();
        loadListMessages(); //Load the messages list

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
            if (queue.isEmpty()) {

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
                Message message = queue.poll(); //Get the first message and delete it
                if (message != null) {
                    if (!message.message().equals("quite")) {

                        Actor newActor = message.actor();

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

                        System.out.println("I received a quite message from " + message.actor().getName());
                        finished = true;

                    }

                }

            }
        }

        System.out.println("Thread finished " + getName());


    }

    public void send(Message message) {
        queue.add(message);
    }

    public String getName() {
        return name;
    }

    public void getMessages() {
        if (!queue.isEmpty()) {
            System.out.println("\nactor " + getName() + " list message");
            System.out.println("--------------------------------------");
            queue.forEach(m -> System.out.println(m.actor().getName() + " says " + "\"" + m.message() + "\"" + " to " + getName()));
            System.out.println("\n");
        }
    }


    public LinkedList<Message> getQueue() {
        return queue;
    }

    private void loadListMessages() {
        messageList.add("Hello Actor!");
        messageList.add("How are you?");
        messageList.add("Nice to see you again!");
        messageList.add("I like Java language, how about you?");
        messageList.add("quite");
    }

    protected void setMessageList(String message) {
        messageList.add(message);
    }

    //Get a random message from messages list
    protected String getMessageFromList() {
        int index = (int) (Math.random() * messageList.size());
        return messageList.get(index);
    }


    //Function run(Start) Thread
    @Override
    public void run() {
        processMessages(); //Call the function which process the messages
    }
}
