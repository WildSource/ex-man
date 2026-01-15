package org.example.mediators.purchaseedit;

import lombok.Getter;
import org.example.components.PurchaseEdit;
import org.example.mediators.internal.Mediator;

public class PurchaseEditMediator implements Mediator<PurchaseEditEvent, Long> {
    @Getter
    private static PurchaseEditMediator instance;
    private PurchaseEdit purchaseEdit;

    private PurchaseEditMediator(PurchaseEdit purchaseEdit) {
        this.purchaseEdit = purchaseEdit;
    }

    public static PurchaseEditMediator getInstance(PurchaseEdit purchaseEdit) {
        if (instance == null) {
            instance = new PurchaseEditMediator(purchaseEdit);
        }
        return instance;
    }

    @Override
    public void notify(PurchaseEditEvent event, Long data) {
        if (event == PurchaseEditEvent.SELECTED) {
            purchaseEdit.setPurchaseId(data);
        }
    }
}
