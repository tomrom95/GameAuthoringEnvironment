package engine.modules;

import engine.IStatus;
import engine.effects.DefaultAffectable;
import engine.events.EventType;
import engine.events.GameEvent;


/**
 * This class implements IStatusModule and handles the status of a sprite over the course of a level
 * and game
 *
 * @author Dhrumil
 *
 */
public class SpriteStatus extends DefaultAffectable implements IStatus {

    private boolean myIsDead;

    @Override
    public void registerEvent (GameEvent event) {
        if (event.getEventType().equals(EventType.DEATH)) {
            myIsDead = true;
        }
    }

    /**
     * Handles removing the sprite from the game after a death condition has been met
     */
    @Override
    public boolean shouldBeRemoved () {
        return myIsDead;
    }

}
