package org.example.mediators.frame;

import lombok.Setter;
import org.example.mediators.internal.Mediator;
import org.example.mediators.internal.MediatorEvent;

import javax.swing.*;

public class FrameMediator implements Mediator {
    @Setter
    private JFrame frame;

    public FrameMediator() {}

    @Override
    public void notify(MediatorEvent event) {
        if (!(event instanceof FrameEvent)) {
            return;
        }

        if (event != FrameEvent.UI_UPDATE) {
            return;
        }

        frame.pack();
    }
}
