import actors.Actor;
import actors.proxys.ActorProxy;
import actors.ring.RingActor;
import actors.singleton.ActorContext;
import message.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class CreateActor extends JFrame {

    private JPanel panel2;
    private JButton createButton;
    private JTextField textField1;


    public CreateActor() {
        setContentPane(panel2);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<Actor> actorsContext = new LinkedList<>();
                if(ActorContext.getActorList().isEmpty()){
                    JOptionPane.showMessageDialog(null, "You can not create a new actor because the program is not running");
                }else{
                    ActorContext.getActorList().forEach((k, v) -> actorsContext.add(v));
                    String name = textField1.getText();
                    Actor prove = new Actor(name);
                    ActorProxy proxy =  ActorContext.spawnActor(prove);
                    actorsContext.getLast().send(new Message(proxy.getActor(), "Hey from a JFrameWindow"));
                    if(ActorContext.getActorList().containsKey(prove.getName()))
                        JOptionPane.showMessageDialog(null, "Actor created successfully");
                    else  JOptionPane.showMessageDialog(null, "Error creating an actor");
                }
            }
        });
    }
}
