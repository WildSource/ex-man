package org.example.components;

import org.example.enums.MediatorEvent;
import org.example.interfaces.Mediator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;

public class SplitPaneMediator extends JSplitPane implements Mediator {
    private static final Logger logger = LoggerFactory.getLogger(SplitPaneMediator.class);

    private SidebarPanel sidebar;
    private TransactionListingPanel transactionListing;

    @Inject
    public SplitPaneMediator(SidebarPanel sidebar, TransactionListingPanel transactionListing) {
        super(JSplitPane.HORIZONTAL_SPLIT, sidebar, transactionListing);
        this.sidebar = sidebar;
        this.transactionListing = transactionListing;

        sidebar.setMediator(this);
        transactionListing.setMediator(this);
    }

    @Override
    public void notify(MediatorEvent event) {
        switch (event) {
            case MediatorEvent.ADD_TRANSACTION -> transactionListing.updateTransactionView();
            case MediatorEvent.MODIFY_TRANSACTION -> logger.info("Modifying transactions is not implemented yet");
            case MediatorEvent.REMOVE_TRANSACTION -> logger.info("Deleting transactions is not implemented yet");
        }
    }
}
