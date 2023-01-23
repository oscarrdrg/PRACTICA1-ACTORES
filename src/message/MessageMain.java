package message;

public class MessageMain extends Message {

    String message;

    public MessageMain( String message) {
        super(null, message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
