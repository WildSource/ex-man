package org.example.components;

import com.github.lgooddatepicker.components.DatePicker;
import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;
import org.example.enums.MediatorEvent;
import org.example.models.DatabaseManager;
import org.example.models.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class TransactionFormPanel extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(TransactionFormPanel.class);

    @Setter
    private SplitPaneMediator mediator;

    private JTextField transactionThing;
    private JTextField transactionAmount;
    private DatePicker transactionDate;
    private JTextField transactionDestinator;

    @Inject
    public  TransactionFormPanel(
            DatePicker datePicker,
            JTextField transactionThing,
            JTextField transactionAmount,
            JTextField transactionDestinator,
            JButton submit
    ) {
        setLayout(new MigLayout(
                "insets 20",
                "[grow, fill][shrink]",
                ""
        ));

        // Instantiate form components
        this.transactionDate = datePicker;
        this.transactionThing = transactionThing;
        this.transactionAmount = transactionAmount;
        this.transactionDestinator = transactionDestinator;

        // Add panel title
        JLabel title = new JLabel("Add New Transaction:");
        title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 18));
        add(title, "wrap, gapbottom 15");

        // Add transaction thing field
        add(new JLabel("Transaction Thing:"), "wrap");
        add(transactionThing);
        add(new JLabel("(What you bought)"), "wrap");

        // Add transaction amount field
        add(new JLabel("Transaction Amount:"), "wrap");
        add(transactionAmount);
        add(new JLabel("(CAD $)"), "wrap");

        // Add transaction date field
        add(new JLabel("Transaction Date:"), "wrap");
        add(transactionDate);
        add(new JLabel("(yyyy-mm-dd)"), "wrap");

        // Add transaction destinator field
        add(new JLabel("Transaction Destinator:"), "wrap");
        add(transactionDestinator);
        add(new JLabel("(From whom was it bought)"), "wrap");

        submit.setText("Save Purchase");
        submit.addActionListener(this::submitTransaction);
        add(submit, "gaptop 15");

        setVisible(true);
    }

    private void submitTransaction(ActionEvent event) {
        // Get data from fields
        String thing = transactionThing.getText();
        var amount = new BigDecimal(transactionAmount.getText());
        LocalDate date = transactionDate.getDate();
        String destinator = transactionDestinator.getText();

        var task = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Create Data entity objects
                var transaction = new Transaction(
                        thing,
                        amount,
                        date,
                        destinator
                );

                DatabaseManager.save(transaction);
                return null;
            }

            @Override
            protected void done() {
                mediator.notify(MediatorEvent.ADD_TRANSACTION);
            }
        };

        task.execute();
    }

}
