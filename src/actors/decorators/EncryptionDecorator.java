package actors.decorators;

import actors.Actor;
import message.Message;

public class EncryptionDecorator extends Actor {

    Actor client;

    public EncryptionDecorator(Actor client) {
        super(client.getName() + " encryption decorator");
        this.client = client;
    }

    public void send(Message message) {

        message.setMessage(encryptMessage(message)); //Encrypt the message
        client.getQueue().add(message);
    }

    public void processMessages() {

        boolean finished = false;

        //In case the process doesn't finish, we're still processing messages
        while (!finished) {
            try {
                Thread.sleep(2000); //Sleep the Thread
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (client.getQueue().isEmpty()) {

                System.out.println("No messages to process " + getName());
                try {
                    Thread.sleep(2000); //Sleep the Thread to process messages in case queue is empty
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            } else {
                try {
                    Thread.sleep(2000); //Sleep the Thread
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                client.getMessages();
                Message message = client.getQueue().poll(); //Get the first message and delete it

                if (message != null) {
                    try {
                        Thread.sleep(2000); //Sleep the Thread
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    message.setMessage(decryptMessage(message.getMessage())); //Decrypt the message
                    try {
                        Thread.sleep(2000); //Sleep the Thread
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (!message.getMessage().equals("quite")) {

                        client.setMessageList("This is a EncryptionDecorator, it's amazing this program!");
                        Actor newActor = message.getActor();

                        try {
                            Thread.sleep(2000); //Sleep the Thread
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        newActor.send(new Message(this, getMessageFromList()));
                        try {
                            Thread.sleep(2000); //Sleep the Thread
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    } else {

                        System.out.println("I received a quite message from " + message.getActor().getName());
                        finished = true;
                    }

                }

            }
        }
    }

    /* Function that decrypt the message,
    takes the message and transforms it into a character array, then subtracts 5 times the character relative
    to the alphabet and transforms it back into a string */
    private String decryptMessage(String message) {
        char[] array = message.toCharArray();

        for (int i = 0; i < array.length; i++) {
            array[i] -= 5;
        }

        return String.valueOf(array);
    }

    /* Function that encrypt the message,
    takes the message and transforms it into a character array, then adds 5 times the character relative
    to the alphabet and transforms it back into a string */
    private String encryptMessage(Message message) {
        char[] array = message.getMessage().toCharArray();
        for (int i = 0; i < array.length; i++) {
            array[i] += 5;
        }

        return String.valueOf(array);
    }

}
