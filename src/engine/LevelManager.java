package engine;

import java.util.List;
import java.util.function.Consumer;
import engine.events.GameEvent;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import graphics.ImageGraphic;
import javafx.collections.FXCollections;
import util.Coordinate;
import util.TimeDuration;


/***
 * This class serves as a container and manager for a collection of the Levels created in the game.
 * This class makes the necessary calls to manage level progression.
 *
 */
public class LevelManager implements ILevelManager {

    private List<ILevel> myLevelPropertyList;
    private ILevel myCurrentLevel;
    private IConditionManager myGlobalGameConditions;

    // since all wrapped in properties, will eventually create lambda loop to call update on all
    // updateable items as
    // specified by our Updateable interface

    public LevelManager () {
        myLevelPropertyList = FXCollections.observableArrayList();
        myCurrentLevel = new Level();
        myGlobalGameConditions = new ConditionManager();
    }

    @Override
    public void bufferedAdd (ISprite sprite, Coordinate coordinate) {
        myCurrentLevel.bufferedAdd(sprite, coordinate);
    }

    @Override
    public void update (TimeDuration duration) {
        // TODO extend this call to include all functionality as required
        checkAndSetCurrentLevel();
        myCurrentLevel.update(duration);
    }

    @Override
    public ILevel getCurrentLevel () {
        return myCurrentLevel;
    }

    @Override
    public List<ILevel> getLevels () {
        return myLevelPropertyList;
    }

    /**
     * Moves to the next level if the current level meets the level progression check
     */
    private void checkAndSetCurrentLevel () {
        if (myCurrentLevel.shouldSwitchLevel()) {
            myCurrentLevel = myCurrentLevel.getNextLevel();
        }
    }

    @Override
    public List<? extends Drawable> getDrawables () {
        return myCurrentLevel.getDrawables();
    }

    @Override
    public void internalizeKeyEvents (List<KeyIOEvent> list) {
        applyToInternalizers(internalize -> internalize.internalizeKeyEvents(list));
    }

    @Override
    public void internalizeMouseEvents (List<MouseIOEvent> list) {
        applyToInternalizers(internalize -> internalize.internalizeMouseEvents(list));
    }

    @Override
    public void internalizeGameEvents (List<GameEvent> list) {
        applyToInternalizers(internalize -> internalize.internalizeGameEvents(list));

    }

    private void applyToInternalizers (Consumer<IEventInternalizer> apply) {
        apply.accept(myCurrentLevel);
        apply.accept(myGlobalGameConditions);
    }

    @Override
    public void remove (ISprite sprite) {
        myCurrentLevel.remove(sprite);
    }

    @Override
    public ImageGraphic getBackgroundImage () {
        return myCurrentLevel.getBackgroundImage();
    }

    @Override
    public void createNewLevel (ILevel newLevel) {
        myCurrentLevel = newLevel;
    }

    @Override
    public void bufferedAdd (ISprite sprite) {
        myCurrentLevel.bufferedAdd(sprite);
    }

    @Override
    public void add (ISprite sprite) {
        myCurrentLevel.add(sprite);
    }

    @Override
    public void add (ISprite sprite, Coordinate coordinate) {
        myCurrentLevel.add(sprite, coordinate);
    }

}
