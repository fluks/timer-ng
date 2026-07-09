package fluks.timerng;

import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

/**
 */
public abstract class ComponentAddListener implements AncestorListener {
    @Override
    public void ancestorRemoved(AncestorEvent ae) { }

    @Override
    public void ancestorMoved(AncestorEvent ae) { } 
}
