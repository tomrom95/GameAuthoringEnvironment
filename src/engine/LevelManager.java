package engine;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;


public class LevelManager implements ILevelManager {

    private ObservableList<ObjectProperty<ILevel>> myLevelPropertyList;
    private ObjectProperty<ILevel> myCurrentLevel;
    // TODO add lists for Game wide attributes
    // TODO add lists for Game wide conditions ?
    // TODO add lists for Game wide attributes

    LevelManager () {
        myLevelPropertyList = FXCollections.observableArrayList();
    }

    @Override
    public void add (ISprite sprite, Coordinate coordinate) {
        // this may not work once we are using attributes in coords
        updateSpriteLocation(sprite, coordinate);
        addSpriteToLevel(myCurrentLevel.get(), sprite);
    }

    @Override
    public void update (TimeDuration duration) {
        // TODO extend this call to include all functionality as required
        checkAndUpdateCurrentLevel();
        updateSprites(myCurrentLevel.get(), duration);
    }

    @Override
    public ObjectProperty<ILevel> getCurrentLevel () {
        return myCurrentLevel;
    }

    @Override
    public ObservableList<ObjectProperty<ILevel>> getLevels () {
        return myLevelPropertyList;
    }

    private void updateSpriteLocation (ISprite sprite, Coordinate coordinate) {
        sprite.getLocation().get().setLocation(coordinate.getX(), coordinate.getY());
    }

    private void updateSprites (ILevel level, TimeDuration duration) {
        for (ObjectProperty<ISprite> s : level.getSprites()) {
            s.get().update(duration);
        }
    }

    private void addSpriteToLevel (ILevel level, ISprite sprite) {
        level.getSprites().add(new SimpleObjectProperty<>(sprite));
    }

    private void checkAndUpdateCurrentLevel () {
        // TODO create logic for figuring out what level is
    }
}
