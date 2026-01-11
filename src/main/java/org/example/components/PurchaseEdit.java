package org.example.components;

import com.github.lgooddatepicker.components.DatePicker;
import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;
import org.example.models.DatabaseManager;
import org.example.models.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

@Getter
public class PurchaseEdit extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseEdit.class);

    private NavigationBar navbar;

    @Setter
    private Long purchaseId;

    private JTextField purchaseItem;
    private JTextField purchasePrice;
    private DatePicker purchaseDate;
    private JTextField purchaseSeller;
    private JButton confirmEdit;

    public PurchaseEdit(NavigationBar navbar) {
        this.purchaseId = -1L;
        this.navbar = navbar;
        setLayout(new MigLayout(
                "insets 20",
                "[grow, fill][shrink]",
                ""
        ));

        // Instantiate form components
        this.purchaseDate = new DatePicker();
        this.purchaseItem = new JTextField();
        this.purchasePrice = new JTextField();
        this.purchaseSeller = new JTextField();
        this.confirmEdit = new JButton("Confirm Edit");

        // Add panel title
        JLabel title = new JLabel("Add New Transaction:");
        title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 18));
        add(title, "wrap, gapbottom 15");

        // Add transaction thing field
        add(new JLabel("Transaction Item:"), "wrap");
        add(purchaseItem);
        add(new JLabel("(What you bought)"), "wrap");

        // Add transaction amount field
        add(new JLabel("Transaction Price:"), "wrap");
        add(purchasePrice);
        add(new JLabel("(CAD $)"), "wrap");

        // Add transaction date field
        add(new JLabel("Transaction Date:"), "wrap");
        add(purchaseDate);
        add(new JLabel("(yyyy-mm-dd)"), "wrap");

        // Add transaction destinator field
        add(new JLabel("Transaction Seller:"), "wrap");
        add(purchaseSeller);
        add(new JLabel("(From whom was it bought)"), "wrap");

        confirmEdit.addActionListener(this::editPurchaseAction);
        add(confirmEdit, "gaptop 15");

        setVisible(true);
    }

    public void updateForm() {
        var sw = new SwingWorker<Purchase,Void>() {
            @Override
            protected Purchase doInBackground() {
                return DatabaseManager.findPurchaseById(purchaseId);
            }

            @Override
            protected void done() {
                try {
                    Purchase purchase = get();
                    purchaseItem.setText(purchase.getItem());
                    purchasePrice.setText(purchase.getPrice().toString());
                    purchaseDate.setDate(purchase.getDate());
                    purchaseSeller.setText(purchase.getSeller());
                } catch (InterruptedException e) {
                    logger.error("Worker thread interrupted while getting purchase data to edit", e);
                } catch (ExecutionException e) {
                    logger.error("Could not get purchase data from worker thread because it got interrupted\n"
                            + "Hint: the interrupted exception might be the cause", e);
                }
            }
        };

        sw.execute();
    }

    public void editPurchaseAction(ActionEvent event) {
        // Get data from fields
        var item = purchaseItem.getText();
        var price = new BigDecimal(purchasePrice.getText());
        LocalDate date = purchaseDate.getDate();
        String seller = purchaseSeller.getText();

        var task = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                var purchase = new Purchase(
                        item,
                        price,
                        date,
                        seller
                );
                DatabaseManager.updatePurchaseById(purchaseId, purchase);
                return null;
            }

            @Override
            protected void done() {
                // Clear form fields
                purchaseItem.setText("");
                purchasePrice.setText("");
                purchaseDate.setDate(null);
                purchaseDate.setText("");
                purchaseSeller.setText("");
                PurchaseListing.updateTransactionView();
                Application.adjust();
            }
        };

        task.execute();
        navbar.showPurchases();
    }
}
