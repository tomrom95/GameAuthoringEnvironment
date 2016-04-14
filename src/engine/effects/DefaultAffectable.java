package engine.effects;

import engine.Affectable;
import engine.IAttribute;
import engine.events.GameEvent;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.TimeDuration;

/**
 * This null class will define default do-nothing behavior for any classes
 * that respond to anything in our events framework
 * 
 * Will extend this, and override methods if the submodule should have actual
 * behavior 
 * @author jonathanim
 *
 */
public abstract class DefaultAffectable implements Affectable {

    @Override
    public void update (TimeDuration duration) {
    }

    @Override
    public void registerEvent (GameEvent event) {
    }

    @Override
    public void applyEffect (IEffect effect) {
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {

    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
    }

    @Override
    public ObservableList<IAttribute> getAttributes () {
        return FXCollections.observableArrayList();
    }

}
