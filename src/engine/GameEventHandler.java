package engine;

import engine.events.GameEvent;


/**
 * TODO add documentation
 *
 */
public interface GameEventHandler {

    /**
     * Respond to a {@link engine.events.GameEvent GameEvent}
     * @param event to which to respond
     */
    void registerEvent (GameEvent event);
}
