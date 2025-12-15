package org.example.components;

import lombok.Getter;
import lombok.Setter;
import org.example.mediators.internal.Mediator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

@Getter
public class SidebarPanel extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(SidebarPanel.class);

    private Mediator mediator;
    @Setter
    private TransactionFormPanel transactionFormPanel;

    @Inject
    public SidebarPanel(TransactionFormPanel transactionFormPanel) {
        this.transactionFormPanel = transactionFormPanel;

        add(transactionFormPanel);
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        setVisible(true);
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
        transactionFormPanel.setMediator(mediator);
    }
}
