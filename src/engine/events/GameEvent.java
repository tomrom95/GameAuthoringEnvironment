package engine.events;

/**
 * This class represents the notion of an event in the game or an action that occurs in the game
 * resulting in unique behavior
 *
 */

public class GameEvent {

    private EventType myType;

    public GameEvent (EventType type) {
        myType = type;
    }
    
    public void setEventType(EventType myType) {
        this.myType = myType;
    }

    public EventType getEventType () {
        return myType;
    }

}
