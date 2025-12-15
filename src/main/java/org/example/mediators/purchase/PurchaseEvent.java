package org.example.mediators.purchase;

import org.example.mediators.internal.MediatorEvent;

public enum PurchaseEvent implements MediatorEvent {
    ADD_TRANSACTION,
    REMOVE_TRANSACTION,
    MODIFY_TRANSACTION
}
