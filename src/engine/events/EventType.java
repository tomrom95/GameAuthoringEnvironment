package engine.events;

import engine.StringBasedType;

public class EventType extends StringBasedType {

    public static final EventType DEATH = new EventType("Death");
    
    public EventType(String type) {
        super(type);
    }
    
    @Override
    protected boolean isSameClass (Object obj) {
        return obj instanceof EventType;
    }
}