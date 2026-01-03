package org.example.components;

import org.example.mediators.frame.FrameMediator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public final class Application extends JFrame {
    private static Application instance;
    private final Logger logger = LoggerFactory.getLogger(Application.class);

    private JPanel panel;
    private CardLayout cardLayout;

    private Application() {
        cardLayout = new CardLayout();
        this.panel = new JPanel(cardLayout);

        panel.add(new PurchaseListing(), "read");
        panel.add(new PurchaseForm(), "create");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Ex-Man");
        add(panel);
        pack();
        setVisible(true);
    }

    public void showPurchases() {
        cardLayout.show(panel, "read");
    }

    public void showPurchaseForm() {
        cardLayout.show(panel, "create");
    }

    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }
}
