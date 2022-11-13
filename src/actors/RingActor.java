package actors;

import message.Message;

import java.util.LinkedList;

public class RingActor {

    private final LinkedList<ActorProxy> proxyList;

    public RingActor() {
        this.proxyList = new LinkedList<>();
    }

    public void addProxies(ActorProxy actorProxy) {
        proxyList.add(actorProxy);

    }

    public void startCommunication() {
        ActorProxy first = proxyList.getFirst();
        ActorProxy last = proxyList.getLast();
        int i = 0;

        while (i <= proxyList.size() - 1) {
            if (i != proxyList.size() - 1) {
                ActorProxy proxy = proxyList.get(i);
                proxy.send(new Message(proxyList.get(i + 1).getActor(), "Start communication"));

            } else
                last.send(new Message(first.getActor(), "Start communication"));
            i++;
        }


    }


}
