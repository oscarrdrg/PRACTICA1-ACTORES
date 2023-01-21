package views;

import actors.singleton.ActorContext;
import controllers.ActorController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActorMessages extends JFrame {
    private JPanel panel4;
    private JPanel panel2;
    private JButton actorMessagesButton;
    private JTextField textField1;

    public ActorMessages(){
        setContentPane(panel4);
        ActorController controller = new ActorController();
        actorMessagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField1.getText();
                JOptionPane.showMessageDialog(null, controller.getMessagesFromController(name));
            }
        });
    }
}
