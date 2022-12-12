package interfaces;

public interface Observer {
     void creation();
     void finalization();
     void incorrect_finalization();
     void received_message();
     
}
