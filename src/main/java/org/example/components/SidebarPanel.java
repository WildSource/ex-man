package org.example.components;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class SidebarPanel {
    private static final Logger logger = LoggerFactory.getLogger(SidebarPanel.class);

    private JPanel panel;
    private TransactionFormPanel transactionFormPanel;

    @Inject
    public SidebarPanel(
            JPanel panel,
            TransactionFormPanel transactionFormPanel
    ) {
        this.panel = panel;
        this.transactionFormPanel = transactionFormPanel;

        panel.add(transactionFormPanel.getPanel());
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panel.setVisible(true);
    }
}
