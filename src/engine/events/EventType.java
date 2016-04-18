package engine.events;

import engine.StringBasedType;


/**
 * This class constructs an event type that reflects a game in the game environment that needs to be
 * accounted for by the objects and conditions in the game
 *
 */

public class EventType extends StringBasedType {

    public static final EventType DEATH = new EventType("Death");
    public static final EventType INVISIBLE = new EventType("Invisible");
    public static final EventType VISIBLE  = new EventType("Visible");

    public EventType (String type) {
        super(type);
    }

    @Override
    protected boolean isSameClass (Object obj) {
        return obj instanceof EventType;
    }
}
