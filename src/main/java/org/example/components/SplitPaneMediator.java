package org.example.components;

import org.example.interfaces.Mediator;

import javax.inject.Inject;
import javax.swing.*;

public class SplitPaneMediator extends JSplitPane implements Mediator {

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
    public void notify(JComponent sender, String event) {
        System.out.println("Received event: " + event + " from sender: " + sender);
    }
}
