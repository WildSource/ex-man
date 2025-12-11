package org.example.components;

import lombok.Getter;
import lombok.Setter;
import org.example.models.DatabaseManager;
import org.example.models.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class TransactionListingPanel {
    private static final Logger logger = LoggerFactory.getLogger(TransactionListingPanel.class);

    private JPanel panel;

    @Inject
    public TransactionListingPanel(JPanel panel) {
        this.panel = panel;

        List<Transaction> transactions = DatabaseManager.findAllTransactions();
        var transactionsView = new JList<Transaction>(transactions.toArray(new Transaction[0]));

        panel.add(transactionsView);
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panel.setVisible(true);
    }
}
