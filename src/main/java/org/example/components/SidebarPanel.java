package org.example.components;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class SidebarPanel {
    private static final Logger logger = LoggerFactory.getLogger(SidebarPanel.class);

    private JPanel panel;
    private TransactionFormPanel transactionFormPanel;

    public SidebarPanel(TransactionFormPanel transactionFormPanel) {
        panel = new JPanel();
        this.transactionFormPanel = transactionFormPanel;
        panel.add(transactionFormPanel.getPanel());
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panel.setVisible(true);
    }
}
