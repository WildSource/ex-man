package org.example.components;

import lombok.Getter;
import lombok.Setter;
import org.example.mediators.purchase.PurchaseMediator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;

public class ApplicationSplitPane extends JSplitPane {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationSplitPane.class);

    @Getter
    private PurchaseMediator purchaseMediator;

    private SidebarPanel sidebar;
    private TransactionListingPanel transactionListing;

    @Inject
    public ApplicationSplitPane(
            PurchaseMediator purchaseMediator,
            SidebarPanel sidebar,
            TransactionListingPanel transactionListing
    ) {
        super(JSplitPane.HORIZONTAL_SPLIT, sidebar, transactionListing);
        this.purchaseMediator = purchaseMediator;
        this.sidebar = sidebar;
        this.transactionListing = transactionListing;

        // Setup dependency of purchase mediator
        purchaseMediator.setTransactionListing(transactionListing);
        sidebar.setMediator(purchaseMediator);
    }

    public void setFrame(JFrame frame) {
        transactionListing.getFrameMediator().setFrame(frame);
    }
}
