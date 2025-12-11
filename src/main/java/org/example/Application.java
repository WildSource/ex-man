package org.example;

import javax.swing.*;

public class Application {
    private final JFrame frame;

    public Application() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Ex-Man");
        frame.setVisible(true);
    }
}
