package engine.modules;

import engine.IAttribute;
import engine.IStatusModule;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import util.TimeDuration;


/**
 * This class implements IStatusModule and handles the status of a sprite over the course of a level
 * and game
 * 
 * @author Dhrumil
 *
 */
public class StatusModule implements IStatusModule {

    @Override
    public void update (TimeDuration duration) {
        // TODO Auto-generated method stub

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
    public ObservableList<ObjectProperty<IAttribute>> getAttributes () {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Handles removing the sprite from the game after a death condition has been met
     */
    @Override
    public boolean shouldBeRemoved () {
        // TODO Auto-generated method stub
        return false;
    }

}
