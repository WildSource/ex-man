package org.example.mediators.purchase;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.components.PurchaseListing;
import org.example.mediators.internal.Mediator;
import org.example.mediators.internal.MediatorEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor
public class PurchaseMediator implements Mediator {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseMediator.class);

    @Setter
    private PurchaseListing transactionListing;

    @Override
    public void notify(MediatorEvent event) {
        if (event instanceof PurchaseEvent mediatorEvent) {
            switch (mediatorEvent) {
                case PurchaseEvent.ADD_TRANSACTION -> transactionListing.updateTransactionView();
                case PurchaseEvent.MODIFY_TRANSACTION -> logger.info("Modifying transactions is not implemented yet");
                case PurchaseEvent.REMOVE_TRANSACTION -> logger.info("Deleting transactions is not implemented yet");
            }
        }
    }
}
