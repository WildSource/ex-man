package org.example;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final String URL = "jdbc:sqlite:exman";

    public static void loadDatabase() {
        try (var connection = DriverManager.getConnection(URL)) {
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void main() {
        SwingUtilities.invokeLater(Application::new);
    }
}
