package engine;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;

public class Level implements ILevel {

    @Override
    public void update (TimeDuration duration) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addCondition (ICondition condition) {
        // TODO Auto-generated method stub

    }

    @Override
    public IConditionManager getConditionManager () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addGlobalResource (IResource resource) {
        // TODO Auto-generated method stub

    }

    @Override
    public ObservableList<SimpleObjectProperty<ISprite>> getSprites () {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public void add (ISprite sprite, Coordinate coordinate) {
        // TODO Auto-generated method stub
        
    }

}
