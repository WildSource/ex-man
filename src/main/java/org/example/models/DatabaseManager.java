package org.example.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Controls the database through its API
public class DatabaseManager {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);
    private static final String URL = "jdbc:sqlite:exman.db";

    // Creates a db file if it does not exist
    // Setups the db
    public static void createDatabase() {
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

    public static void savePurchase(Purchase purchase) {
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
            pstmt.setString(1, purchase.getItem());
            // TODO make so that 12.99 is stored as 1299 in db
            pstmt.setInt(2, purchase.getPrice().intValue());
            pstmt.setString(3, purchase.getDate().toString());
            pstmt.setString(4, purchase.getSeller());
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
    
    public static void deletePurchaseById(Long purchaseId) {
        var deleteStatement =
                """
                DELETE FROM PURCHASE WHERE ID = ?
                """;

        try (var conn = DriverManager.getConnection(URL);
             var pstmt = conn.prepareStatement(deleteStatement)) {

            pstmt.setLong(1, purchaseId);

            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Could not delete purchase with id: " + purchaseId);
        }
    }

    public static List<Purchase> findAllPurchases() {
        var queryAllCommand = "SELECT * FROM PURCHASE";
        List<Purchase> purchases = new ArrayList<>();

        try (var connexion = DriverManager.getConnection(URL)) {
            var stmt = connexion.createStatement();
            ResultSet transactionsRs = stmt.executeQuery(queryAllCommand);

            while (transactionsRs.next()) {
                purchases.add(
                        new Purchase(
                                transactionsRs.getLong("ID"),
                                transactionsRs.getString("ITEM"),
                                new BigDecimal(transactionsRs.getInt("PRICE")),
                                LocalDate.parse(transactionsRs.getString("DATE")),
                                transactionsRs.getString("SELLER")
                        )
                );
            }
        } catch (SQLException e) {
            logger.error("The database could not retrieve transactions", e);
        }
        return purchases;
    }
}
