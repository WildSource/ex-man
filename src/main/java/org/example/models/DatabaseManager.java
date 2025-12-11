package org.example.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Controls the database through its API
public class DatabaseManager {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);
    private static final String URL = "jdbc:sqlite:exman.db";

    // Creates a db file if it does not exist
    // Setups the db
    public static void createDatabase() {
        // TODO rename model (and their attributes) to match db table
        var createTransactionTable =
                """
                CREATE TABLE IF NOT EXISTS PURCHASE (
                    ID INTEGER PRIMARY KEY NOT NULL,
                    ITEM TEXT NOT NULL,
                    PRICE INTEGER NOT NULL,
                    DATE TEXT NOT NULL,
                    SELLER TEXT NOT NULL
                );
                """;

        try (var connection = DriverManager.getConnection(URL)) {
            logger.info("Connected to database successfully");
            connection.setAutoCommit(false);

            var stmt = connection.createStatement();
            stmt.execute(createTransactionTable);

            connection.commit();
        } catch (SQLException e) {
            logger.error("Database initialization failed", e);
        }
    }

    public static void save(Transaction transaction) {
        Connection connexion = null;
        var insertCommand =
                """
                INSERT INTO PURCHASE (
                    ITEM,
                    PRICE,
                    DATE,
                    SELLER
                ) VALUES (?, ? , ?, ?)
                """;

        try {
            connexion = DriverManager.getConnection(URL);
            connexion.setAutoCommit(false);

            var pstmt = connexion.prepareStatement(insertCommand);
            pstmt.setString(1, transaction.getThing());
            // TODO make so that 12.99 is stored as 1299 in db
            pstmt.setInt(2, transaction.getAmount().intValue());
            pstmt.setString(3, transaction.getDate().toString());
            pstmt.setString(4, transaction.getDestinator());
            pstmt.execute();

            connexion.commit();
        } catch (SQLException e) {
            logger.error("Failed to Save transaction", e);
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ex) {
                logger.error("The database connexion could not rollback", e);
            }
        } finally {
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException e) {
                    logger.error("The database connexion could not disconnect");
                }
            }
        }
    }
}
