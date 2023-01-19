package views;

import actors.Actor;
import actors.proxys.ActorProxy;
import actors.singleton.ActorContext;
import controllers.ActorController;
import message.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class CreateActor extends JFrame {

    private JPanel panel2;
    private JButton createButton;
    private JTextField textField1;


    public CreateActor() {
        ActorController controller = new ActorController();
        setContentPane(panel2);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField1.getText();

                if (controller.createActor(name) == 1) {
                    JOptionPane.showMessageDialog(null, "You can not create a new actor because the program is not running");
                } else {
                    if (controller.createActor(name) == 2)
                        JOptionPane.showMessageDialog(null, "Actor created successfully");
                    else JOptionPane.showMessageDialog(null, "Error creating an actor");
                }
            }
        });
    }
}
