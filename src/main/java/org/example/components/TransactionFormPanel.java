package org.example.components;

import com.github.lgooddatepicker.components.DatePicker;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.awt.*;

@Setter
@Getter
public class TransactionFormPanel {
    private static final Logger logger = LoggerFactory.getLogger(TransactionFormPanel.class);

    private JPanel panel;
    private JTextField transactionThing;
    private JTextField transactionAmount;
    private DatePicker transactionDate;
    private JTextField transactionDestinator;

    @Inject
    public  TransactionFormPanel(
            @Named("TFormMigPanel") JPanel panel,
            DatePicker datePicker,
            JTextField transactionThing,
            JTextField transactionAmount,
            JTextField transactionDestinator
    ) {
        // Instantiate form components
        this.panel = panel;
        this.transactionDate = datePicker;
        this.transactionThing = transactionThing;
        this.transactionAmount = transactionAmount;
        this.transactionDestinator = transactionDestinator;

        // Add panel title
        JLabel title = new JLabel("Add New Transaction:");
        title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 18));
        panel.add(title, "wrap");

        // Add transaction thing field
        panel.add(new JLabel("Transaction Thing:"), "wrap");
        panel.add(transactionThing);
        panel.add(new JLabel("(What you bought)"), "wrap");

        // Add transaction amount field
        panel.add(new JLabel("Transaction Amount:"), "wrap");
        panel.add(transactionAmount);
        panel.add(new JLabel("(CAD $)"), "wrap");

        // Add transaction date field
        panel.add(new JLabel("Transaction Date:"), "wrap");
        panel.add(transactionDate);
        panel.add(new JLabel("(yyyy-mm-dd)"), "wrap");

        // Add transaction destinator field
        panel.add(new JLabel("Transaction Destinator:"), "wrap");
        panel.add(transactionDestinator);
        panel.add(new JLabel("(From whom was it bought)"), "wrap");

        panel.setVisible(true);
    }

}
