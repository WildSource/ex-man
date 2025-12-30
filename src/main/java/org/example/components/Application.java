package org.example.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;

public class Application extends JFrame {
    private final Logger logger = LoggerFactory.getLogger(Application.class);
    private final JSplitPane splitPane;

    @Inject
    public Application(ApplicationSplitPane splitPane) {
        this.splitPane = splitPane;
        splitPane.setFrame(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Ex-Man");
        add(splitPane);
        pack();
        setVisible(true);
    }
}
