package org.example.components;

import com.github.lgooddatepicker.components.DatePicker;
import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class PurchaseEdit extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseEdit.class);

    private NavigationBar navbar;

    private JTextField transactionThing;
    private JTextField transactionAmount;
    private DatePicker transactionDate;
    private JTextField transactionDestinator;
    private JButton confirmEdit;

    public PurchaseEdit(NavigationBar navbar) {
        this.navbar = navbar;
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
        this.confirmEdit = new JButton("Confirm Edit");

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

        confirmEdit.addActionListener((_) -> System.out.println());
        add(confirmEdit, "gaptop 15");

        setVisible(true);
    }
}
