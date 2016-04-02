package engine;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;

public class Level implements ILevel {
    
    private ObjectProperty<IConditionManager> myConditionManager;
    private ObjectProperty<ISpriteManager> mySpriteManager;
    private ObjectProperty<IAttributeManager> myAttributeManager;
    private ObjectProperty<INextLevelManager> myNextLevelManager;
    
    Level(){
        //TODO need to actually instantiate internal manager objects 
        //after creating the concrete classes
        myAttributeManager = new SimpleObjectProperty<>(new AttributeManager());
        myConditionManager = new SimpleObjectProperty<>(new ConditionManager());
        mySpriteManager = new SimpleObjectProperty<>(new SpriteManager());
        myNextLevelManager = new SimpleObjectProperty<>(new NextLevelManager());
    }

    @Override
    public void update (TimeDuration duration) {
        mySpriteManager.get().update(duration);
        myConditionManager.get().update(duration);
        myAttributeManager.get().update(duration);
        myNextLevelManager.get().update(duration);
    }


    @Override
    public ObservableList<ObjectProperty<ICondition>> getConditionsPropertyList () {
        return myConditionManager.get().getConditionListProperty();
    }

    @Override
    public void addGlobalResource (IResource resource) {
        myAttributeManager.get().addResource(resource);
    }

    @Override
    public ObservableList<ObjectProperty<ISprite>> getSprites () {
        return mySpriteManager.get().getSprites();
    }



    @Override
    public void add (ISprite sprite, Coordinate coordinate) {
       mySpriteManager.get().add(sprite, coordinate);
    }

    @Override
    public ILevel getNextLevel () {
        return myNextLevelManager.get().getNextLevel();
    }

    @Override
    public boolean shouldSwitchLevel () {
        return myNextLevelManager.get().shouldGoToNextLevel();
    }

}
