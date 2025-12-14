package org.example.interfaces;

import org.example.enums.MediatorEvent;

public interface Mediator {
    void notify(MediatorEvent event);
}
