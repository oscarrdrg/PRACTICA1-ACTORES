package services;

import actors.Actor;
import actors.observer.ActorListener;
import actors.singleton.ActorContext;
import interfaces.Observer;
import message.Message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MonitorService{

    private static final ActorListener listener = new ActorListener("Listener");
    private static final LinkedList<String> actorMonitorList = new LinkedList();
    public static void addActorsMonitor(Actor a) {
        actorMonitorList.add(a.getName());
    }

    public static Actor getActorFromMonitorService(String name) {
        List<String> actorInList = actorMonitorList.stream().filter(actorName -> actorName.equals(name)).collect(Collectors.toList());
        String actorName = actorInList.get(0);
        return ActorContext.getActorFromList(actorName);
    }

    public static String getTraffic(Actor actor) {
        int cont = 0;
        if (getActorFromMonitorService(actor.getName()) != null) {
            for (int i = 0; i < actor.getQueue().size(); i++) cont++;
        }

        if (cont < 5) return "Low messages traffic -> " + cont + " messages in " + actor.getName();
        else if ((cont >= 5) && (cont <= 15))
            return "Medium message traffic ->" + cont + " messages in " + actor.getName();
        return "High message traffic ->" + cont + " messages in " + actor.getName();
    }

    public static ArrayList<String> getActorMessages(Actor actor){
        ArrayList<String> messageList = new ArrayList<>();
        if (getActorFromMonitorService(actor.getName()) != null) {
            actor.getQueue().forEach(message -> {messageList.add(message.getMessage());});
            return messageList;
        }

        return null;
    }

    public static void notifyMessage(String message, Actor actor) {
        switch (message) {
            case "Creation":
                listener.creation(actor);
                break;
            case "Finalization":
                listener.finalization(actor);
                break;
            case "Incorrect Finalization":
                listener.incorrect_finalization(actor);
                break;
            case "Message Received":
                listener.received_message(actor);
                break;
        }

    }

}
