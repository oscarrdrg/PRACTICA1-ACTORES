package interfaces;

import java.util.LinkedList;

public interface InsultService {

    void addInsult(String insult);
    LinkedList<String> getAllInsults();
    String getInsult();
}
