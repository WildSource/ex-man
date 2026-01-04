package org.example.mediators.internal;

public interface Mediator<T> {
    void notify(MediatorEvent event, T data);
}
