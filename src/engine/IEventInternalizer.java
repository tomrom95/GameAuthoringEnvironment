package engine;

import java.util.List;
import engine.events.GameEvent;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;

/**
 * An interface that forces response to game-wide events
 * @author jonathanim
 *
 */
public interface IEventInternalizer {

    /**
     * Respond to key-press events or delegate to submodules to respond
     * @param list of {@link engine.interactionevents.KeyIOEvent KeyIOEvents}
     */
    void internalizeKeyEvents (List<KeyIOEvent> list);

    /**
     * Respond to mouse-click events or delegate to submodules to respond
     * @param list of {@link engine.interactionevents.MouseIOEvent MouseIOEvent}
     */
    void internalizeMouseEvents (List<MouseIOEvent> list);

    /**
     * Respond to internal game events or delegate to submodules to respond
     * @param list of {@link engine.events.GameEvent GameEvent}
     */
    void internalizeGameEvents (List<GameEvent> list);
    
    

}
