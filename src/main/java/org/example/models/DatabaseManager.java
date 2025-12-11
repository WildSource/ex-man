package org.example.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.SQLException;

// Controls the database through its API
public class DatabaseManager {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);
    private static final String URL = "jdbc:sqlite:exman.db";

    // Creates a db file if it does not exist
    public static void createDatabase() {
        try (var _ = DriverManager.getConnection(URL)) {
            logger.info("Connected to database successfully");
        } catch (SQLException e) {
            logger.error("Failed to connect to database", e);
        }
    }
}
