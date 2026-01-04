package org.example.mediators.frame;


import org.example.components.Application;
import org.example.mediators.internal.Mediator;
import org.example.mediators.internal.MediatorEvent;

import javax.swing.*;

public class FrameMediator implements Mediator {
    private static FrameMediator instance;

    private final Application frame;

    private FrameMediator() {
        this.frame = Application.getInstance();
    }

    public static FrameMediator getInstance() {
        if (instance == null) {
            instance = new FrameMediator();
        }
        return instance;
    }

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
