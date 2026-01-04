package org.example.components;

import javax.swing.*;
import java.awt.*;

public class NavigationBar extends JPanel {
    private CardLayout cardLayout;
    private JPanel panel;

    private JButton addPurchaseButton;
    private JButton showPurchasesButton;

    public NavigationBar(CardLayout cardLayout, JPanel panel) {
        this.cardLayout = cardLayout;
        this.panel = panel;

        this.addPurchaseButton = new JButton("Add Purchase");
        addPurchaseButton.addActionListener((_) -> showPurchaseForm());
        add(addPurchaseButton);

        this.showPurchasesButton = new JButton("Show Purchases");
        showPurchasesButton.addActionListener((_) -> showPurchases());
        add(showPurchasesButton);
        showPurchases();
    }

    public void showPurchases() {
        cardLayout.show(panel, "read");
        addPurchaseButton.setEnabled(true);
        showPurchasesButton.setEnabled(false);
    }

    public void showPurchaseForm() {
        cardLayout.show(panel, "create");
        addPurchaseButton.setEnabled(false);
        showPurchasesButton.setEnabled(true);
    }
}
