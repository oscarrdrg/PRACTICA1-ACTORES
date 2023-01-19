package main;

import views.StartWindow;

import javax.swing.*;

/**
 * @author Oscar
 */
public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new StartWindow();
                frame.setSize(300, 300);
                frame.setVisible(true);
            }
        });
    }
}