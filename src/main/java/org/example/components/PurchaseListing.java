package org.example.components;

import lombok.Getter;
import net.miginfocom.swing.MigLayout;
import org.example.mediators.frame.FrameMediator;
import org.example.mediators.frame.FrameMediatorEvent;
import org.example.mediators.navigationbar.NavigationBarMediator;
import org.example.mediators.navigationbar.NavigationEvent;
import org.example.models.DatabaseManager;
import org.example.models.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Getter
public class PurchaseListing extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseListing.class);

    private final Provider<FrameMediator> frameMediatorProvider;
    private final NavigationBarMediator navigationBarMediator;

    private static final DefaultListModel<Purchase> observableTransactions = new DefaultListModel<>();
    private final JList<Purchase> transactionsView;

    @Getter
    private static Long selectedPurchaseId;

    @Inject
    public PurchaseListing(
            Provider<FrameMediator> frameMediatorProvider,
            NavigationBarMediator navigationBarMediator
    ) {
        this.frameMediatorProvider = frameMediatorProvider;
        this.navigationBarMediator = navigationBarMediator;

        selectedPurchaseId = -1L;

        this.transactionsView = new JList<>();

        transactionsView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        transactionsView.setModel(observableTransactions);
        transactionsView.addListSelectionListener(this::onSelectedPurchase);

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
                return DatabaseManager.findAllPurchases();
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
                        observableTransactions.clear();
                        purchases.forEach((observableTransactions::addElement));
                    }
                    frameMediatorProvider.get().notify(FrameMediatorEvent.UI_RESIZE, null);
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

    public void onSelectedPurchase(ListSelectionEvent listSelectionEvent) {
        // Confirms that it not in the middle of changing
        if (listSelectionEvent.getValueIsAdjusting()) {
            return;
        }

        // Check if something is actually selected
        if (transactionsView.getSelectedIndex() == -1) {
            return;
        }

        // Set current selected id
        selectedPurchaseId = transactionsView
                .getModel()
                .getElementAt(transactionsView.getSelectedIndex())
                .getId();

        logger.info("Purchase Selected with id: " + selectedPurchaseId);

        // Enable delete button
        navigationBarMediator.notify(NavigationEvent.PURCHASE_SELECTED, null);
    }
}
