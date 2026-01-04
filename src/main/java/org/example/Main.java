package org.example;

import com.formdev.flatlaf.FlatLightLaf;
import org.example.components.Application;
import org.example.models.DatabaseManager;

import javax.swing.*;

public class Main {
    static void main() {
        DatabaseManager.createDatabase(); // Database
        FlatLightLaf.setup(); // UI Theme
        SwingUtilities.invokeLater(Application::getInstance); // Swing Application
    }
}
