package actors.proxys;

import message.Message;

import java.util.LinkedList;

public class ActorProxyExtended extends ActorProxy{

    LinkedList<Message> messageLinkedList = new LinkedList<>();
    public ActorProxyExtended(String name) {
        super(name);
    }

    @Override
    public void send(Message message) {
       messageLinkedList.add(message);
    }

    public LinkedList<Message> getMessageLinkedList() {
        return messageLinkedList;
    }

    public void setMessageLinkedList(LinkedList<Message> messageLinkedList) {
        this.messageLinkedList = messageLinkedList;
    }
}

