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
    // TODO add Attribute manager
    // TODO add Condition manager
    // since all wrapped in properties, will eventually create lambda loop to call update on all updateable items as 
    // specified by our Updateable interface

    LevelManager () {
        myLevelPropertyList = FXCollections.observableArrayList();
    }

    @Override
    public void add (ISprite sprite, Coordinate coordinate) {
        // this may not work once we are using attributes in coords
        myCurrentLevel.get().add(sprite, coordinate);
    }

    @Override
    public void update (TimeDuration duration) {
        // TODO extend this call to include all functionality as required
        checkAndUpdateCurrentLevel();
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



    private void checkAndUpdateCurrentLevel () {
        // TODO create logic for figuring out what level is
    }
}
