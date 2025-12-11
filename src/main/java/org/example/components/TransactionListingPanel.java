package org.example.components;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class TransactionListingPanel {
    private static final Logger logger = LoggerFactory.getLogger(TransactionListingPanel.class);

    private JPanel panel;

    public TransactionListingPanel() {
        panel = new JPanel();
        panel.add(new JLabel("Hello Listing Transactions"));
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panel.setVisible(true);
    }
}
