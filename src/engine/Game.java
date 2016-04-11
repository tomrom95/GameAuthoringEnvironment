package engine;

import java.util.List;
import engine.AttributeManager;
import engine.AuthorshipData;
import engine.ConditionManager;
import engine.Drawable;
import engine.GameInformation;
import engine.IAttribute;
import engine.IAttributeManager;
import engine.IConditionManager;
import engine.IGame;
import engine.IGameInformation;
import engine.ILevelManager;
import engine.LevelManager;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import graphics.ImageGraphic;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import util.TimeDuration;


/**
 * This class manages and structures the layout for the components of a game.
 * 
 * @author RyanStPierre
 *
 */
public class Game implements IGame {

    private ILevelManager myLevelManager;
    private IConditionManager myConditionManager;
    private AuthorshipData myAuthorshipData;
    private IGameInformation myGameInformation;
    private IAttributeManager myAttributeManager;


    public Game (LevelManager levelManager,
                 GameInformation info,
                 ConditionManager conditionManager) {
        myLevelManager = levelManager;
        myConditionManager = conditionManager;
        myAuthorshipData = new AuthorshipData();
        myAttributeManager = new AttributeManager();
    }

    @Override
    public void update (TimeDuration duration) {
        myLevelManager.update(duration);
        myConditionManager.update(duration);
    }

    @Override
    public IGameInformation getGameInformation () {
        return myGameInformation;
    }

    @Override
    public IAttributeManager getAttributeManager () {
        return myAttributeManager;
    }

    @Override
    public ILevelManager getLevelManager () {
       
        return myLevelManager;
    }

    @Override
    public IConditionManager getConditionManager () {
        return myConditionManager;
    }

    @Override
    public ObservableList<? extends Drawable> getDrawables () {
       return myLevelManager.getDrawables();
    }

    @Override
    public void internalizeKeyEvents (List<KeyIOEvent> list) {
        myLevelManager.internalizeKeyEvents(list);
        myConditionManager.internalizeKeyEvents(list);
        

    }

    @Override
    public void internalizeMouseEvents (List<MouseIOEvent> list) {
        myLevelManager.internalizeMouseEvents(list);
        myConditionManager.internalizeMouseEvents(list);
    }

    @Override
    public AuthorshipData getAuthorshipData () {
        return myAuthorshipData;
    }

    @Override
    public ImageGraphic getBackroundImage () {
        return myLevelManager.getBackgroundImage();
    }

    @Override
    public ObservableList<ObjectProperty<IAttribute>> getGlobalAttributes () {
        return getAttributeManager().getAttributes();
    }

}

