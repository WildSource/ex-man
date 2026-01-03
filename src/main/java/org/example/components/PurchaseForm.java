package org.example.components;

import com.github.lgooddatepicker.components.DatePicker;
import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;
import org.example.mediators.purchase.PurchaseEvent;
import org.example.mediators.internal.Mediator;
import org.example.models.DatabaseManager;
import org.example.models.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class PurchaseForm extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseForm.class);

    @Setter
    private Mediator mediator;

    private JTextField transactionThing;
    private JTextField transactionAmount;
    private DatePicker transactionDate;
    private JTextField transactionDestinator;
    private JButton submit;

    public PurchaseForm(
    ) {
        setLayout(new MigLayout(
                "insets 20",
                "[grow, fill][shrink]",
                ""
        ));

        // Instantiate form components
        this.transactionDate = new DatePicker();
        this.transactionThing = new JTextField();
        this.transactionAmount = new JTextField();
        this.transactionDestinator = new JTextField();
        this.submit = new JButton("Save Purchase");

        // Add panel title
        JLabel title = new JLabel("Add New Transaction:");
        title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 18));
        add(title, "wrap, gapbottom 15");

        // Add transaction thing field
        add(new JLabel("Transaction Item:"), "wrap");
        add(transactionThing);
        add(new JLabel("(What you bought)"), "wrap");

        // Add transaction amount field
        add(new JLabel("Transaction Price:"), "wrap");
        add(transactionAmount);
        add(new JLabel("(CAD $)"), "wrap");

        // Add transaction date field
        add(new JLabel("Transaction Date:"), "wrap");
        add(transactionDate);
        add(new JLabel("(yyyy-mm-dd)"), "wrap");

        // Add transaction destinator field
        add(new JLabel("Transaction Seller:"), "wrap");
        add(transactionDestinator);
        add(new JLabel("(From whom was it bought)"), "wrap");

        submit.addActionListener(this::submitTransaction);
        add(submit, "gaptop 15");

        setVisible(true);
    }

    private void submitTransaction(ActionEvent event) {
        // Get data from fields
        String item = transactionThing.getText();
        var price = new BigDecimal(transactionAmount.getText());
        LocalDate date = transactionDate.getDate();
        String seller = transactionDestinator.getText();

        var task = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Create Data entity objects
                var transaction = new Purchase(
                        item,
                        price,
                        date,
                        seller
                );

                DatabaseManager.save(transaction);
                return null;
            }

            @Override
            protected void done() {
                mediator.notify(PurchaseEvent.ADD_TRANSACTION);

                // Clear form fields
                transactionThing.setText("");
                transactionAmount.setText("");
                transactionDate.setDate(null);
                transactionDate.setText("");
                transactionDestinator.setText("");
            }
        };

        task.execute();
    }
}
