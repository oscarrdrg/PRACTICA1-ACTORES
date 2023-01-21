package controllers;

import actors.Actor;
import actors.proxys.ActorProxy;
import actors.singleton.ActorContext;
import services.MonitorService;
import message.Message;

import java.util.ArrayList;
import java.util.LinkedList;

public class ActorController {
    public ActorController() {
    }

    public int createActor(String name) {
        LinkedList<Actor> actorsContext = new LinkedList<>();
        if (ActorContext.getActorList().isEmpty()) {
            return 1;
        } else {
            ActorContext.getActorList().forEach((k, v) -> actorsContext.add(v));
            Actor prove = new Actor(name);
            ActorProxy proxy = ActorContext.spawnActor(prove);
            actorsContext.getLast().send(new Message(proxy.getActor(), "Hey from a JFrameWindow"));
            if (ActorContext.getActorList().containsKey(prove.getName()))
                return 2;
            else return 0;
        }
    }

    public ArrayList<String> getMessagesFromController(String name) {
        Actor actor = ActorContext.getActorFromList(name);
        if (MonitorService.getActorMonitorList().contains(actor)) return MonitorService.getActorMessages(actor);
        else {
            ArrayList<String> list = new ArrayList<>();
            list.add("No actor in Monitor Service");
            return list;

        }

    }

    public String getTrafficFromActor(String name) {
        Actor actor = ActorContext.getActorFromList(name);
        if (!MonitorService.getActorMonitorList().contains(actor)) return "No actor in Monitor Service";
        else return MonitorService.getTraffic(actor);
    }
}
