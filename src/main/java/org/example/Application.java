package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;

public class Application {
    private final Logger logger = LoggerFactory.getLogger(Application.class);
    private final JFrame frame;
    private final JSplitPane splitPane;

    @Inject
    public Application(JFrame frame, @Named("App SplitPane") JSplitPane splitPane) {
        this.frame = frame;
        this.splitPane = splitPane;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Ex-Man");
        frame.add(splitPane);
        frame.pack();
        frame.setVisible(true);
    }
}
