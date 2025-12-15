package org.example.components;

import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;
import org.example.models.DatabaseManager;
import org.example.models.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private DefaultListModel<Purchase> observableTransactions;
    private JList<Purchase> transactionsView;

    @Inject
    public TransactionListingPanel(
            DefaultListModel<Purchase> observableTransactions,
            JList<Purchase> transactionsView
    ) {
        this.observableTransactions = observableTransactions;
        this.transactionsView = transactionsView;

        transactionsView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        transactionsView.setModel(observableTransactions);

        setLayout(new MigLayout());
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        add(new JLabel("Your Transactions:"), "wrap, gaptop 10");
        add(transactionsView);

        updateTransactionView();

        setVisible(true);
    }

    public void updateTransactionView() {
        var sw = new SwingWorker<List<Purchase>, Void>() {
            @Override
            protected List<Purchase> doInBackground() {
                return DatabaseManager.findAllTransactions();
            }

            @Override
            protected void done() {
                try {
                    // Change implementation
                    // In the future with a growing database this will not be viable
                    List<Purchase> purchases = get();

                    if (observableTransactions.isEmpty()) {
                        observableTransactions.addAll(purchases);
                    } else {
                        purchases.forEach((purchase -> {
                            if (!observableTransactions.contains(purchase)) {
                                observableTransactions.addElement(purchase);
                            }
                        }));
                    }
                } catch (InterruptedException e) {
                    logger.error("Worker thread got interrupted while querying for transactions", e);
                } catch (ExecutionException e) {
                    logger.error("Could not get transactions from worker thread because it got interrupted\n"
                            + "Hint: the interrupted exception might be the cause", e);
                }
            }
        };

        sw.execute();
    }
}
