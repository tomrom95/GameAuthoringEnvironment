package engine;

import java.util.List;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import util.TimeDuration;

/**
 * Responsible for managing the components of the game 
 * @author RyanStPierre
 *
 */
public class Game implements IGame, IGamePlayable {

    private ILevelManager myLevelManager;
    private IConditionManager myConditionManager;
    private AuthorshipData myAuthorshipData;
    private IGameInformation myGameInformation;
    
    public Game (LevelManager levelManager, GameInformation info, ConditionManager conditionManager) {
        myLevelManager = levelManager;
        myConditionManager = conditionManager;
        myAuthorshipData = new AuthorshipData();
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
    public ObservableList<IAttribute> getGlobalAttributes () {
        // TODO Auto-generated method stub
        return null;
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
    public ObservableList<? extends ObjectProperty<? extends Drawable>> getDrawables () {
       return myLevelManager.getDrawables();
    }

    @Override
    public void internalizeKeyEvents (List<KeyIOEvent> list) {
        myLevelManager.internalizeKeyEvents(list);
        
    }

    @Override
    public void internalizeMouseEvents (List<MouseIOEvent> list) {
        myLevelManager.internalizeMouseEvents(list);
    }

    @Override
    public AuthorshipData getAuthorshipData () {
       return myAuthorshipData;
    }

}
