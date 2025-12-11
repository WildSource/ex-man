package org.example.providers;

import net.miginfocom.swing.MigLayout;
import org.codejargon.feather.Provides;
import org.example.components.SidebarPanel;
import org.example.components.TransactionListingPanel;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;

public class JComponentProvider {
    @Provides
    @Named("App SplitPane")
    @Inject
    JSplitPane provideSplit(
            SidebarPanel sidebar,
            TransactionListingPanel transactionListing
    ) {
        return new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                sidebar.getPanel(),
                transactionListing.getPanel()
        );
    }

    @Provides
    @Named("TFormMigPanel")
    JPanel provideTransactionFormMigPanel() {
        return new JPanel(new MigLayout(
                "insets 20",
                "[grow, fill][shrink]",
                "[]20[]5[]"
        ));
    }

}
