package org.example.components;

import net.miginfocom.swing.MigLayout;
import org.example.mediators.frame.FrameMediator;
import org.example.mediators.purchaselisting.PurchaseListingMediator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;

@Singleton
public final class Application extends JFrame {
    private final Logger logger = LoggerFactory.getLogger(Application.class);

    private JPanel panel;
    private CardLayout cardLayout;
    private NavigationBar navbar;

    @Inject
    private Application(
            Provider<FrameMediator> frameMediatorProvider,
            Provider<PurchaseListingMediator> purchaseListingMediatorProvider
    ) {
        this.cardLayout = new CardLayout();
        this.panel = new JPanel(cardLayout);
        this.navbar = NavigationBar.getInstance(cardLayout, panel);

        panel.add(new PurchaseListing(frameMediatorProvider), "read");
        panel.add(new PurchaseForm(
                navbar,
                frameMediatorProvider,
                purchaseListingMediatorProvider
        ), "create");
        panel.add(new PurchaseEdit(navbar), "edit");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new MigLayout());
        setLocationRelativeTo(null);
        setTitle("Ex-Man");
        add(panel, "wrap");
        add(navbar);
        pack();
        setVisible(true);
    }
}
