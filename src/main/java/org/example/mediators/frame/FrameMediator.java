package org.example.mediators.frame;

import org.example.components.Application;
import org.example.mediators.internal.Mediator;

import javax.inject.Inject;

public class FrameMediator implements Mediator<FrameMediatorEvent, Void> {
    private Application frame;

    @Inject
    public FrameMediator(Application frame) {
        this.frame = frame;
    }

    @Override
    public void notify(FrameMediatorEvent event, Void data) {
        if (event == FrameMediatorEvent.UI_RESIZE) {
            System.out.println("Frame resize call");
            frame.pack();
        }
    }
}
