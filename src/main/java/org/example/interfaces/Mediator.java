package org.example.interfaces;

import javax.swing.*;

public interface Mediator {
    void notify(JComponent sender, String event);
}
