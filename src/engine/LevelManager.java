package engine;

import java.util.List;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;


public class LevelManager implements ILevelManager {

    private ObservableList<ObjectProperty<ILevel>> myLevelPropertyList;
    private ObjectProperty<ILevel> myCurrentLevel;
    private ObjectProperty<IConditionManager> myGlobalGameConditions;
    private ObjectProperty<IAttributeManager> myGlobalAttributeManager;

    // since all wrapped in properties, will eventually create lambda loop to call update on all
    // updateable items as
    // specified by our Updateable interface

    public LevelManager (ObjectProperty<ILevel> startingLevel) {
        myLevelPropertyList = FXCollections.observableArrayList();
        myCurrentLevel = startingLevel; 
    }

    @Override
    public void add (ISprite sprite, Coordinate coordinate) {
        myCurrentLevel.get().add(sprite, coordinate);
    }

    @Override
    public void update (TimeDuration duration) {
        // TODO extend this call to include all functionality as required
        checkAndSetCurrentLevel();
        myCurrentLevel.get().update(duration);
    }

    @Override
    public ObjectProperty<ILevel> getCurrentLevel () {
        return myCurrentLevel;
    }

    @Override
    public ObservableList<ObjectProperty<ILevel>> getLevels () {
        return myLevelPropertyList;
    }

    private void checkAndSetCurrentLevel () {
        if (myCurrentLevel.get().shouldSwitchLevel()) {
            myCurrentLevel.set(myCurrentLevel.get().getNextLevel());
        }
    }

    @Override
    public ObservableList<? extends ObjectProperty<? extends Drawable>> getDrawables () {
        return myCurrentLevel.get().getDrawables();
    }

    @Override
    public void internalizeKeyEvents (List<KeyIOEvent> list) {
        myCurrentLevel.get().internalizeKeyEvents(list);
        
    }

    @Override
    public void internalizeMouseEvents (List<MouseIOEvent> list) {
        myCurrentLevel.get().internalizeMouseEvents(list);
        
    }

    @Override
    public void remove (ObjectProperty<ISprite> sprite) {
        myCurrentLevel.get().remove(sprite);
        
    }
}
