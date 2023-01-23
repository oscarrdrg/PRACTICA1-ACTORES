package actors.decorators;

import actors.Actor;
import message.Message;

import java.sql.SQLOutput;

public class EncryptionDecorator extends Actor {

    Actor client;

    public EncryptionDecorator(Actor client) {
        super(client.getName() + " encryption decorator");
        this.client = client;
    }

    public void send(Message message) {
        message.setMessage(encryptMessage(message)); //Encrypt the message
        client.send(message);
    }

    public void processMessages(Message message) {
        message.setMessage(decryptMessage(message.getMessage()));
        client.processMessages(message);
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
