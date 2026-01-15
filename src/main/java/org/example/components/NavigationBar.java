package org.example.components;

import lombok.Getter;
import org.example.mediators.purchaselisting.PurchaseListingMediator;
import org.example.mediators.purchaselisting.PurchaseListingMediatorEvent;
import org.example.models.DatabaseManager;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.*;
import java.awt.*;

public class NavigationBar extends JPanel {
    private Provider<PurchaseListingMediator> purchaseListingMediatorProvider;

    private CardLayout cardLayout;
    private JPanel panel;

    private JButton addPurchaseButton;
    private JButton showPurchasesButton;
    @Getter
    private JButton editPurchaseButton;
    @Getter
    private JButton deleteSelectedPurchaseButton;

    @Inject
    public NavigationBar(
            CardLayout cardLayout,
            JPanel panel,
            Provider<PurchaseListingMediator> purchaseListingMediatorProvider
    ) {
        this.cardLayout = cardLayout;
        this.panel = panel;
        this.purchaseListingMediatorProvider = purchaseListingMediatorProvider;

        this.addPurchaseButton = new JButton("Add Purchase");
        addPurchaseButton.addActionListener((_) -> showPurchaseForm());
        add(addPurchaseButton);

        this.showPurchasesButton = new JButton("Show Purchases");
        showPurchasesButton.addActionListener((_) -> showPurchases());
        add(showPurchasesButton);

        this.editPurchaseButton = new JButton("Edit Purchase");
        editPurchaseButton.addActionListener((_) -> showEditPurchasePanel());
        add(editPurchaseButton);

        this.deleteSelectedPurchaseButton = new JButton("Delete Purchase");
        deleteSelectedPurchaseButton.addActionListener((_) -> deleteSelectedPurchase());
        add(deleteSelectedPurchaseButton);

        showPurchases();
    }

    public void showPurchases() {
        cardLayout.show(panel, "read");
        addPurchaseButton.setEnabled(true);
        showPurchasesButton.setEnabled(false);
        editPurchaseButton.setEnabled(false);
        deleteSelectedPurchaseButton.setEnabled(false);
    }

    public void showPurchaseForm() {
        cardLayout.show(panel, "create");
        addPurchaseButton.setEnabled(false);
        showPurchasesButton.setEnabled(true);
        editPurchaseButton.setEnabled(false);
        deleteSelectedPurchaseButton.setEnabled(false);
    }

    public void showEditPurchasePanel() {
        cardLayout.show(panel, "edit");
        addPurchaseButton.setEnabled(false);
        showPurchasesButton.setEnabled(true);
        deleteSelectedPurchaseButton.setEnabled(true);
        editPurchaseButton.setEnabled(false);
    }

    public void deleteSelectedPurchase() {
        var sw = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                DatabaseManager.deletePurchaseById(PurchaseListing.getSelectedPurchaseId());
                return null;
            }

            @Override
            protected void done() {
                purchaseListingMediatorProvider.get().notify(PurchaseListingMediatorEvent.UPDATE_LISTING, null);
            }
        };

        sw.execute();
    }
}
