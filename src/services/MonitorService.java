package services;

import actors.Actor;
import interfaces.Observer;
import message.Message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MonitorService {

    private static final LinkedList<Observer> observerList =  new LinkedList<>();
    private static final LinkedList<Actor> actorMonitorList = new LinkedList();

    public static void addObservers(Observer o) {
        observerList.add(o);
    }

    public static void addActorsMonitor(Actor a) {
        actorMonitorList.add(a);
    }

    public static void removeObservers(Observer o) {
        observerList.remove(o);
    }


    public static LinkedList<Observer> getObserverList() {
        return observerList;
    }

    public static void setObserverList(LinkedList<Observer> observerList) {
        observerList = observerList;
    }

    public static LinkedList<Actor> getActorMonitorList() {
        return actorMonitorList;
    }

    public static Actor getActorFromMonitorService(String name) {
        List<Actor> actorInList = actorMonitorList.stream().filter(actor -> actor.getName().equals(name)).collect(Collectors.toList());
        return actorInList.get(0);
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

    public static void notifyMessage(String message) {
        switch (message) {
            case "Creation":
                observerList.forEach(o -> o.creation());
                break;
            case "Finalization":
                observerList.forEach(o -> o.finalization());
                break;
            case "Incorrect Finalization":
                observerList.forEach(o -> o.incorrect_finalization());
                break;
            case "Message Received":
                observerList.forEach(o -> o.received_message());
                break;
        }


    }

}
