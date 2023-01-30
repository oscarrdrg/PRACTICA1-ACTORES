package actors;

import message.Message;

import java.util.LinkedList;

/**
 * @author Oscar
 */

public class InsultActor extends Actor {
    private LinkedList<String> list;

    public InsultActor(String name) {
        super(name);
        list = new LinkedList<>();
        loadList();
    }

    public void loadList() {
        list.add("Idiot");
        list.add("Jerk");
        list.add("Bastard");
    }

    public String getInsultMessage() {
        int index = (int) (Math.random() * list.size());
        return list.get(index);
    }

    public void addInsult(String message) {
        list.add(message);
    }

    public LinkedList<String> getAllMessages() {
        return list;
    }

    public void processMessage(Message message) {

        Actor newActor = message.getActor();

        try {
            Thread.sleep(2000); //Sleep the Thread
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        newActor.send(new Message(this, getInsultMessage()));
        try {
            Thread.sleep(2000); //Sleep the Thread
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
