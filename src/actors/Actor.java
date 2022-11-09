package actors;

import java.util.LinkedList;
import java.util.Objects;

import interfaces.SendMessage;
import message.Message;

/**
 * @author Oscar
 */

public class Actor implements SendMessage, Runnable {

    private String name;
    private final LinkedList<Message> queue;

    /*List of possibles messages that this actor could send to others*/
    private final LinkedList<String> messageList = new LinkedList<>();

    public Actor(String name) {
        this.name = name;
        queue = new LinkedList<>();
        loadListMessages(); //Load the messages list

    }

    public void processMessages() {

        boolean finished = false;

        //In case the process doesn't finish, we're still processing messages
        while (!finished) {
            if (queue.isEmpty()) {

                System.out.println("No messages to process " + getName());
                try {
                    Thread.sleep(2000); //Sleep the Thread to process messages in case queue is empty
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            } else {
                getMessages();
                Message message = queue.poll(); //Get the first message and delete it
                if (message != null) {
                    if (!message.getMessage().equals("quite")) {

                        Actor newActor = message.getActor();
                        newActor.send(new Message(this, getMessageFromList()));

                    } else {

                        System.out.println("I received a quite message from " + message.getActor().getName());
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

    public void setName(String name) {
        this.name = name;
    }

    public void getMessages() {
        System.out.println("\nactor " + getName() + " list message");
        System.out.println("--------------------------------------");
        queue.forEach(m -> System.out.println(m.getActor().getName() + " says " + "\"" + m.getMessage() + "\"" + " to " + getName()));
        System.out.println("\n");
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

    protected void setMessageList(String message){
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
