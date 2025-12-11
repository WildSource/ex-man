package org.example;

import org.example.components.SidebarPanel;
import org.example.components.TransactionFormPanel;
import org.example.components.TransactionListingPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class Application {
    private Logger logger = LoggerFactory.getLogger(Application.class);
    private final JFrame frame;
    private final JSplitPane splitPane;

    public Application() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Ex-Man");

        splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new SidebarPanel(new TransactionFormPanel()).getPanel(),
                new TransactionListingPanel().getPanel()
        );

        frame.add(splitPane);
        frame.pack();
        frame.setVisible(true);
    }
}
