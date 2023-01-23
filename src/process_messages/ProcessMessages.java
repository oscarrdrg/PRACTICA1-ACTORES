package process_messages;

import actors.Actor;
import message.Message;
import services.MonitorService;

public class ProcessMessages implements Runnable {
    Actor actor;
    public ProcessMessages(Actor actor) {
        this.actor = actor;

    }

    public void run() {
        boolean finished = false;

        //In case the process doesn't finish, we're still processing messages
        while (!finished) {
            try {
                Thread.sleep(2000); //Sleep the Thread
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (actor.getQueue().isEmpty()) {

                System.out.println("No messages to process: " + actor.getName());
                try {
                    Thread.sleep(2000); //Sleep the Thread to process messages in case queue is empty
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            } else {
                try {
                    Thread.sleep(2000); //Sleep the Thread to process messages in case queue is empty
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                actor.getMessages();
                Message message = actor.getQueue().poll();//Get the first message and delete it
                if (message != null) {
                    if (!message.getMessage().equals("quite")) {
                        actor.processMessages(message);
                    } else {
                        System.out.println("I received a quite message from " + message.getActor().getName());
                        finished = true;
                        MonitorService.notifyMessage("Finalization", actor);
                    }

                }

            }

        }

    }
}
