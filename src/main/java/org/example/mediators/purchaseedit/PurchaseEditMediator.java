package org.example.mediators.purchaseedit;

import org.example.components.PurchaseEdit;
import org.example.mediators.internal.Mediator;

public class PurchaseEditMediator implements Mediator<PurchaseEditEvent, Long> {
    private PurchaseEdit purchaseEdit;

    public PurchaseEditMediator(PurchaseEdit purchaseEdit) {
        this.purchaseEdit = purchaseEdit;
    }

    @Override
    public void notify(PurchaseEditEvent event, Long data) {
        if (event == PurchaseEditEvent.SELECTED) {
            purchaseEdit.setPurchaseId(data);
        }
    }
}
