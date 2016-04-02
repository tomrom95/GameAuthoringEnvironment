package engine;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;


public class LevelManager implements ILevelManager {

    private ObservableList<ObjectProperty<ISprite>> mySpritePropertyList;
    private ObservableList<ObjectProperty<ILevel>> myLevelPropertyList;
    // TODO add lists for Game wide attributes
    // TODO add lists for Game wide conditions ?
    // TODO add lists for Game wide attributes

    LevelManager () {
        mySpritePropertyList = FXCollections.observableArrayList();
        myLevelPropertyList = FXCollections.observableArrayList();
    }

    @Override
    public void add (ISprite sprite, Coordinate coordinate) {
        // this may not work once we are using attributes in coords
        sprite.getLocation().get().setLocation(coordinate.getX(), coordinate.getY()); 
        mySpritePropertyList.add(new SimpleObjectProperty<ISprite>(sprite));
    }

    @Override
    public void update (TimeDuration duration) {
        for (ObjectProperty<ISprite> s : mySpritePropertyList) {
            s.get().update(duration);
        }

    }

    @Override
    public ILevel getCurrentLevel () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObservableList<ObjectProperty<ILevel>> getLevels () {
        return myLevelPropertyList;
    }

}
