package interfaces;

import actors.Actor;

public interface Observer {
     void creation(Actor a);
     void finalization(Actor a);
     void incorrect_finalization(Actor a);
     void received_message(Actor a);
     
}
