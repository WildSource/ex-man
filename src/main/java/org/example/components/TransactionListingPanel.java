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
public class TransactionListingPanel {
    private static final Logger logger = LoggerFactory.getLogger(TransactionListingPanel.class);

    private JPanel panel;

    @Inject
    public TransactionListingPanel(JPanel panel) {
        this.panel = panel;
        panel.add(new JLabel("Hello Listing Transactions"));
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panel.setVisible(true);
    }
}
