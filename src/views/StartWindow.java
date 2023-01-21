package views;

import controllers.StartController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JFrame{
    private JPanel panel1;
    public JButton startProgramButton;
    private JButton actorsInContextButton;
    private JButton salirButton;
    private JButton createActorButton;
    private JButton actorActionsButton;

    public StartWindow(){
        setContentPane(panel1);
        StartController controller = new StartController();
        startProgramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.start();
            }
        });
        actorsInContextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              JOptionPane.showMessageDialog(null, controller.actorsContext());
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.exit();
            }
        });
        createActorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new CreateActor();
                        frame.setSize(300, 300);
                        frame.setVisible(true);
                    }
                });
            }
        });
        actorActionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new ActorActions();
                        frame.setSize(300, 300);
                        frame.setVisible(true);
                    }
                });

            }
        });
    }
}
