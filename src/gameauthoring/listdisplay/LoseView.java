package gameauthoring.listdisplay;

import engine.IGame;
import engine.ILevel;
import engine.events.EventType;

public class LoseView extends EndView {

    public LoseView (IGame game, ILevel level) {
        super(game, level);
    }

    @Override
    protected EventType getEventType () {
        return EventType.LOSE;
    }
    
    

    
}
