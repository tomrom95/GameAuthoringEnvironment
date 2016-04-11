package engine;

import engine.events.GameEvent;


public interface GameEventHandler {

    void registerEvent (GameEvent event);
}
