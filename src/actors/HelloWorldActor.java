package actors;

import message.Message;

public class HelloWorldActor extends Actor{
    public HelloWorldActor(String name) {
        super(name);
    }

    @Override
    public void processMessages(Message message) {
        Actor actor = message.getActor();
        actor.send(new Message(this, "Hello World, I'm HelloWorldActor"));
    }
}
