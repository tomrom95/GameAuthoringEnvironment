package gameauthoring.listdisplay;

import engine.events.EventType;


/**
 * Factory to interpret String choice by the user and make the proper event
 * Can be refactored later to include more elegant ways of doing this conversion
 * 
 * @author RyanStPierre
 *
 */
public class EventTypeFactory {

    public EventType interpret (String input) {
        switch (input) {
            case "Win":
                return EventType.WIN;
            case "Lose":
                return EventType.LOSE;
            default:
                return EventType.WIN;
        }

    }
}
