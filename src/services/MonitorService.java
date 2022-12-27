package services;

import interfaces.Observer;

import java.util.LinkedList;

public class MonitorService {

    private LinkedList<Observer> observerList;

    public MonitorService() {
        this.observerList = new LinkedList<>();
    }

    public void addObservers(Observer o) {
        observerList.add(o);
    }

    public void removeObservers(Observer o) {
        observerList.remove(o);
    }

    public void notifyMessage(String message) {
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
