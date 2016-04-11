package engine.modules;

import java.util.List;
import engine.IAttribute;
import engine.IStatus;
import engine.effects.IEffect;
import engine.events.EventType;
import engine.events.GameEvent;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import util.TimeDuration;


/**
 * This class implements IStatusModule and handles the status of a sprite over the course of a level
 * and game
 *
 * @author Dhrumil
 *
 */
public class SpriteStatus implements IStatus {

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

    @Override
    public void applyEffect (IEffect effect) {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<IAttribute> getAttributes () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update (TimeDuration duration) {
        // TODO Auto-generated method stub

    }

}
