package views;

import controllers.ActorController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActorTraffic extends JFrame {
    private JPanel panel2;
    private JButton actorMessagesButton;
    private JTextField textField1;
    private JPanel panel5;

    public ActorTraffic(){
        setContentPane(panel5);
        ActorController controller = new ActorController();
        actorMessagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField1.getText();
                JOptionPane.showMessageDialog(null, controller.getTrafficFromActor(name));
            }
        });
    }
}
