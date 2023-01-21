package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActorActions extends JFrame {
    private JPanel panel3;
    private JButton actorMessagesButton;
    private JButton actorTrafficButton;

    public ActorActions(){
        setContentPane(panel3);
        actorMessagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new ActorMessages();
                        frame.setSize(300, 300);
                        frame.setVisible(true);
                    }
                });
            }
        });
        actorTrafficButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new ActorTraffic();
                        frame.setSize(300, 300);
                        frame.setVisible(true);
                    }
                });

            }
        });
    }


}
