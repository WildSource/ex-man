package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String URL = "jdbc:sqlite:exman.db";

    public static void loadDatabase() {
        try (var _ = DriverManager.getConnection(URL)) {
            logger.info("Connected to database successfully");
        } catch (SQLException e) {
            logger.error("Failed to connect to database", e);
        }
    }

    static void main() {
        // Called to create the db file
        // in case it doesn't exist
        loadDatabase();
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(Application::new);
    }
}
