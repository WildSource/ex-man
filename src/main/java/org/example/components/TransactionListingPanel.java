package org.example.components;

import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;
import org.example.models.DatabaseManager;
import org.example.models.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlite.SQLiteConfig;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Getter
@Setter
public class TransactionListingPanel extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(TransactionListingPanel.class);

    @Setter
    private SplitPaneMediator mediator;

    private ListModel<Transaction> observableTransactions;
    private JList<Transaction> transactionsView;

    @Inject
    public TransactionListingPanel(
            DefaultListModel<Transaction> observableTransactions,
            JList<Transaction> transactionsView
    ) {
        this.observableTransactions = observableTransactions;
        this.transactionsView = transactionsView;

        transactionsView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setLayout(new MigLayout());
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        add(new JLabel("Your Transactions:"), "wrap, gaptop 10");

        var sw = new SwingWorker<List<Transaction>, Void>() {
            @Override
            protected List<Transaction> doInBackground() throws Exception {
                return DatabaseManager.findAllTransactions();
            }

            @Override
            protected void done() {
                try {
                    List<Transaction> transactions = get();

                    observableTransactions.addAll(transactions);
                    transactionsView.setModel(observableTransactions);
                    add(transactionsView);
                } catch (InterruptedException e) {
                    logger.error("Worker thread got interrupted while querying for transactions", e);
                } catch (ExecutionException e) {
                    logger.error("Could not get transactions from worker thread because it got interrupted\n"
                    + "Hint: the interrupted exception might be the cause", e);
                }
            }
        };

        sw.execute();

        setVisible(true);
    }
}
