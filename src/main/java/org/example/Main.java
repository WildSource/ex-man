package org.example;

import com.formdev.flatlaf.FlatLightLaf;
import org.codejargon.feather.Feather;
import org.example.components.Application;
import org.example.models.DatabaseManager;
import org.example.providers.JComponentProvider;

import javax.swing.*;

public class Main {
    static void main() {
        Feather feather = Feather.with(new JComponentProvider()); // Dependency Injection
        DatabaseManager.createDatabase(); // Database
        FlatLightLaf.setup(); // UI Theme
        SwingUtilities.invokeLater(() -> feather.instance(Application.class)); // Swing Application
    }
}
