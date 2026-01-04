package org.example.components;

import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public final class Application extends JFrame {
    private static Application instance;
    private final Logger logger = LoggerFactory.getLogger(Application.class);

    private JPanel panel;
    private CardLayout cardLayout;
    private NavigationBar navbar;

    private Application() {
        this.cardLayout = new CardLayout();
        this.panel = new JPanel(cardLayout);
        this.navbar = new NavigationBar(cardLayout, panel);

        panel.add(new PurchaseListing(), "read");
        panel.add(new PurchaseForm(navbar), "create");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new MigLayout());
        setLocationRelativeTo(null);
        setTitle("Ex-Man");
        add(panel, "wrap");
        add(navbar);
        pack();
        setVisible(true);
    }

    public static void adjust() {
        getInstance().pack();
    }

    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }
}
