package engine.events;

public class GameEvent {
    
    private EventType myType;
    
    public GameEvent (EventType type) {
        myType = type;
    }
    
    public EventType getEventType () {
        return myType;
    }

}
