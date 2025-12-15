package org.example;

import com.formdev.flatlaf.FlatLightLaf;
import org.codejargon.feather.Feather;
import org.example.components.Application;
import org.example.models.DatabaseManager;
import org.example.providers.SwingComponentProvider;

import javax.swing.*;

public class Main {
    static void main() {
        Feather feather = Feather.with(new SwingComponentProvider()); // Dependency Injection
        DatabaseManager.createDatabase(); // Database
        FlatLightLaf.setup(); // UI Theme
        SwingUtilities.invokeLater(() -> feather.instance(Application.class)); // Swing Application
    }
}
