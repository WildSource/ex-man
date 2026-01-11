package org.example.mediators.internal;

public interface Mediator<T, U> {
    void notify(T event, U data);
}
