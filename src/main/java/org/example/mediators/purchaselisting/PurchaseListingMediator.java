package org.example.mediators.purchaselisting;

import org.example.components.PurchaseListing;
import org.example.mediators.internal.Mediator;

import javax.inject.Inject;

public class PurchaseListingMediator implements Mediator<PurchaseListingMediatorEvent, Void> {
    private PurchaseListing purchaseListing;

    @Inject
    public PurchaseListingMediator(PurchaseListing purchaseListing) {
        this.purchaseListing = purchaseListing;
    }

    @Override
    public void notify(PurchaseListingMediatorEvent event, Void data) {
        if (event == PurchaseListingMediatorEvent.UPDATE_LISTING) {
            purchaseListing.updateTransactionView();
        }
    }
}
