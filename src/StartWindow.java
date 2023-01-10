import actors.singleton.ActorContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class StartWindow extends JFrame{
    private JPanel panel1;
    public JButton startProgramButton;
    private JButton actorsInContextButton;
    private JButton salirButton;
    private JButton createActorButton;

    public StartWindow(){
        setContentPane(panel1);
        startProgramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartProgram.intro();
                StartProgram.startProgram();
            }
        });
        actorsInContextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<String> actorsContext = new LinkedList<>();
                ActorContext.getActorList().forEach((k, v) -> actorsContext.add(k));
                if(actorsContext.isEmpty())  JOptionPane.showMessageDialog(null, "No actors in Actor Context");
                else JOptionPane.showMessageDialog(null, actorsContext);
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
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
    }
}
