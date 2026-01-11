package org.example.mediators.navigationbar;

import org.example.components.NavigationBar;
import org.example.mediators.internal.Mediator;

public final class NavigationBarMediator implements Mediator<NavigationEvent, Void> {
    private static NavigationBarMediator instance;

    private static NavigationBar navbar;

    private NavigationBarMediator() {
        navbar = NavigationBar.getInstance();
    }

    public static NavigationBarMediator getInstance() {
        if (instance == null) {
            instance = new NavigationBarMediator();
        }
        return instance;
    }

    @Override
    public void notify(NavigationEvent event, Void data) {
        navbar.getEditPurchaseButton().setEnabled(true);
        navbar.getDeleteSelectedPurchaseButton().setEnabled(true);
    }
}
