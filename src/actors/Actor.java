package actors;

import java.util.LinkedList;

import interfaces.SendMessage;
import message.Message;
import process_messages.ProcessMessages;
import services.MonitorService;

/**
 * @author Oscar
 */

public class Actor implements SendMessage {

    private final String name;
    private MonitorService monitorService;
    private final LinkedList<Message> queue;

    /*List of possibles messages that this actor could send to others*/
    private final LinkedList<String> messageList = new LinkedList<>();

    public Actor(String name) {
        this.name = name;
        queue = new LinkedList<>();
        loadListMessages(); //Load the messages list
    }

    public MonitorService getMonitorService() {
        return monitorService;
    }

    public void setMonitorService(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    public void processMessages(Message message) {
        Actor newActor = message.getActor();
        try {
            Thread.sleep(2000); //Sleep the Thread
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (newActor != null) newActor.send(new Message(this, getMessageFromList()));
        try {
            Thread.sleep(2000); //Sleep the Thread
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void send(Message message) {
        MonitorService.notifyMessage("Message Received", this);
        queue.add(message);
    }

    public String getName() {
        return name;
    }

    public void getMessages() {
        if (!queue.isEmpty()) {
            System.out.println("\nactor " + getName() + " list message");
            System.out.println("--------------------------------------");
            queue.forEach(m -> {
                if (m.getActor() != null)
                    System.out.println(m.getActor().getName() + " says " + "\"" + m.getMessage() + "\"" + " to " + getName());
            });
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

    public void setMessageList(String message) {
        messageList.add(message);
    }

    //Get a random message from messages list
    public String getMessageFromList() {
        int index = (int) (Math.random() * messageList.size());
        return messageList.get(index);
    }

    public LinkedList<String> getMessageList() {
        return messageList;
    }


}
