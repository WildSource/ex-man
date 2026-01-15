package org.example.mediators.navigationbar;

import org.example.components.NavigationBar;
import org.example.mediators.internal.Mediator;

import javax.inject.Inject;

public class NavigationBarMediator implements Mediator<NavigationEvent, Void> {
    private NavigationBar navbar;

    @Inject
    public NavigationBarMediator(NavigationBar navbar) {
        this.navbar = navbar;
    }

    @Override
    public void notify(NavigationEvent event, Void data) {
        navbar.getEditPurchaseButton().setEnabled(true);
        navbar.getDeleteSelectedPurchaseButton().setEnabled(true);
    }
}
